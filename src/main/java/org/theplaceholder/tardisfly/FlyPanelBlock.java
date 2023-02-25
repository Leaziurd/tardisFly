package org.theplaceholder.tardisfly;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.tileentities.ConsoleTile;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.cap.fly.PlayerTardisFlyCapability;
import org.theplaceholder.tardisfly.cap.tfly.TardisFlyCapability;
import org.theplaceholder.tardisfly.interfaces.ExteriorTileMixinInterface;
import org.theplaceholder.tardisfly.network.ExteriorRotationS2CPacket;
import org.theplaceholder.tardisfly.network.TardisFlyPacket;
import org.theplaceholder.tardisfly.utils.ExteriorsIDs;

import java.util.Objects;

public class FlyPanelBlock extends Block {

    public FlyPanelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult brtr){
        if(!world.isClientSide){
            if(TardisHelper.getConsoleInWorld(world).isPresent()){
                ConsoleTile console = TardisHelper.getConsoleInWorld(world).get();
                PlayerTardisFlyCapability cap = (PlayerTardisFlyCapability) Capabilities.getPlayerTardisFlyCapability(player);
                TardisFlyCapability tardisFlyCapability = (TardisFlyCapability) world.getCapability(Capabilities.TARDIS_FLY).orElse(null);
                if ((Objects.equals(cap.getTardisID(), "0") || cap.getTardisID() == null) && tardisFlyCapability != null) {
                    if (!console.isInFlight() && Objects.equals(tardisFlyCapability.getFlyingPlayerUUID(), "0")) {
                        TardisFly.NETWORK.send(PacketDistributor.ALL.noArg(), new TardisFlyPacket(player.getUUID(), ExteriorsIDs.getExteriorID(TardisHelper.getConsoleInWorld(world).get())));
                        Vars.playerExteriorMap.put(player.getUUID().toString(), ExteriorsIDs.getExteriorID(TardisHelper.getConsoleInWorld(world).get()));
                        cap.setTardisID(world.dimension().location().getPath());
                        cap.setTardisX((int) player.getX());
                        cap.setTardisY((int) player.getY());
                        cap.setTardisZ((int) player.getZ());

                        cap.setTardisYaw((int) player.yRot);
                        cap.setTardisPitch((int) player.xRot);

                        BlockPos blockPos = console.getCurrentLocation();
                        WorldHelper.teleportEntities(player, world.getServer().getLevel(console.getCurrentDimension()), blockPos, 0, 0);

                        console.getExteriorType().remove(console);

                        console.getDoor().ifPresent(door -> door.setOpenState(EnumDoorState.CLOSED));
                        world.getCapability(Capabilities.TARDIS_FLY).ifPresent(capability -> capability.setFlyingPlayerUUID(player.getUUID().toString()));

                        console.getOrFindExteriorTile().ifPresent(tile -> {
                            TardisFly.NETWORK.send(PacketDistributor.ALL.noArg(), new ExteriorRotationS2CPacket(player.getUUID().toString(), ((ExteriorTileMixinInterface)tile).getRot()));
                        });

                        Minecraft.getInstance().options.setCameraType(PointOfView.THIRD_PERSON_BACK);
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
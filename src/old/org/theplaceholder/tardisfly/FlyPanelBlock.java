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
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.cap.fly.PlayerTardisFlyCapability;

import java.util.Objects;

public class FlyPanelBlock extends Block {

    public FlyPanelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult brtr){

            if ((Objects.equals(cap.getTardisID(), "0") || cap.getTardisID() == null)) {
                TardisHelper.getConsoleInWorld(world).ifPresent(console -> {
                    BlockPos blockPos = console.getCurrentLocation();
                    if (!console.isInFlight()) {
                        cap.setTardisID(world.dimension().location().getPath());

                        cap.setTardisX((int) player.getX());
                        cap.setTardisY((int) player.getY());
                        cap.setTardisZ((int) player.getZ());
                        
                        cap.setTardisYaw((int) player.yRot);
                        cap.setTardisPitch((int) player.xRot);

                        cap.setTardisExteriorID(ExteriorsIDs.getExteriorID(console));

                        WorldHelper.teleportEntities(player, world.getServer().getLevel(console.getCurrentDimension()), blockPos, 0, 0);
                        console.getExteriorType().remove(console);

                        console.getDoor().ifPresent(door -> door.setOpenState(EnumDoorState.CLOSED));
                        world.getCapability(Capabilities.TARDIS_FLY).ifPresent(capability -> capability.setFlyingPlayerUUID(player.getUUID().toString()));

                        Minecraft.getInstance().options.setCameraType(PointOfView.THIRD_PERSON_BACK);
                    }
                });
            }
        }
        return ActionResultType.SUCCESS;
    }
}
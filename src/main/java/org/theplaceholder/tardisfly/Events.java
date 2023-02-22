package org.theplaceholder.tardisfly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.cap.fly.IPlayerTardisFly;
import org.theplaceholder.tardisfly.cap.fly.PlayerTardisFlyCapability;
import org.theplaceholder.tardisfly.cap.tfly.ITardisFly;
import org.theplaceholder.tardisfly.cap.tfly.TardisFlyCapability;
import org.theplaceholder.tardisfly.network.TardisFlyRemovePacket;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TardisFly.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events {
    @SubscribeEvent
    public static void onEntityAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            if (!event.getObject().getCapability(Capabilities.PLAYER_TARDIS_FLY).isPresent()) {
                event.addCapability(new ResourceLocation("tardisfly", "fly_cap"), new IPlayerTardisFly.Provider(new PlayerTardisFlyCapability()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onWorldAttachCapabilities(AttachCapabilitiesEvent<World> event) {
        if (event.getObject() instanceof ServerWorld) {
            if (!event.getObject().getCapability(Capabilities.TARDIS_FLY).isPresent() && TardisHelper.getConsoleInWorld(event.getObject()).isPresent()){
                event.addCapability(new ResourceLocation("tardisfly", "fly_cap"), new ITardisFly.Provider(new TardisFlyCapability()));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerSneak(TickEvent.PlayerTickEvent event) {
        if (event.player != null){
            if (!event.player.level.isClientSide) {
                if (event.player.isCrouching() && event.player.isOnGround()) {
                    event.player.getCapability(Capabilities.PLAYER_TARDIS_FLY).ifPresent(cap -> {
                        if (cap.getTardisID() != null && !Objects.equals(cap.getTardisID(), "0")) {
                            ServerWorld tWorld = event.player.level.getServer().getLevel(WorldHelper.getWorldKeyFromRL(new ResourceLocation("tardis", cap.getTardisID())));

                            TardisHelper.getConsoleInWorld(tWorld).ifPresent((console) -> {
                                console.setDestination(event.player.level.dimension(), event.player.blockPosition());
                                console.setCurrentLocation(event.player.level.dimension(), event.player.blockPosition());

                                console.getExteriorType().place(console, event.player.level.dimension(), event.player.blockPosition());
                                cap.setTardisID("0");
                                BlockPos blockPos = new BlockPos(cap.getTardisX(), cap.getTardisY(), cap.getTardisZ());
                                WorldHelper.teleportEntities(event.player, tWorld, blockPos, cap.getTardisYaw(), cap.getTardisPitch());
                                tWorld.getCapability(Capabilities.TARDIS_FLY).ifPresent(capability -> capability.setFlyingPlayerUUID("0"));

                                Minecraft.getInstance().options.setCameraType(PointOfView.FIRST_PERSON);

                                TardisFly.NETWORK.send(PacketDistributor.ALL.noArg(), new TardisFlyRemovePacket(event.player.getUUID()));
                            });
                        }
                    });
                }
            }
        }
    }
}
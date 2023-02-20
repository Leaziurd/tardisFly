package org.theplaceholder.tardisfly;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.tileentities.console.misc.ExteriorPropertyManager;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.cap.fly.IPlayerTardisFly;
import org.theplaceholder.tardisfly.cap.fly.PlayerTardisFlyCapability;

import java.util.Objects;

@Mod("tardisfly")
public class TardisFly {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "tardisfly");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "tardisfly");

    public TardisFly() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        RegistryObject<Block> block = BLOCKS.register("fly_panel", () -> new FlyPanelBlock(AbstractBlock.Properties.of(Material.STONE)));
        ITEMS.register("fly_panel", () -> new BlockItem(block.get(), new Item.Properties()));

        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
    @Mod.EventBusSubscriber(modid = "tardisfly", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof PlayerEntity) {
                if (!event.getObject().getCapability(Capabilities.PLAYER_TARDIS_FLY).isPresent()) {
                    event.addCapability(new ResourceLocation("tardisfly", "fly_cap"), new IPlayerTardisFly.Provider(new PlayerTardisFlyCapability()));
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
                                });
                            }
                        });
                    }
                }
            }
        }

    }
}
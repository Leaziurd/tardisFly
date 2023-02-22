package org.theplaceholder.tardisfly;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.theplaceholder.tardisfly.network.TardisFlyPacket;
import org.theplaceholder.tardisfly.network.TardisFlyRemovePacket;

import java.util.Optional;

@Mod(TardisFly.MODID)
public class TardisFly {
    public static final String MODID = "tardisfly";

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "networking"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public TardisFly() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        RegistryObject<Block> block = BLOCKS.register("fly_panel", () -> new FlyPanelBlock(AbstractBlock.Properties.of(Material.STONE)));
        ITEMS.register("fly_panel", () -> new BlockItem(block.get(), new Item.Properties()));

        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(FMLCommonSetupEvent event) {
        int index = 0;
        NETWORK.registerMessage(index++, TardisFlyPacket.class, TardisFlyPacket::encode, TardisFlyPacket::decode, TardisFlyPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        NETWORK.registerMessage(index++, TardisFlyRemovePacket.class, TardisFlyRemovePacket::encode, TardisFlyRemovePacket::decode, TardisFlyRemovePacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }
}
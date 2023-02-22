package org.theplaceholder.tardisfly;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.theplaceholder.tardisfly.network.TardisFlyPacket;

import java.util.Optional;

@Mod(TardisFly.MODID)
public class TardisFly {
    public static final String MODID = "tardisfly";

    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "networking"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public TardisFly() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        int index = 0;
        NETWORK.registerMessage(index++, TardisFlyPacket.class, TardisFlyPacket::encode, TardisFlyPacket::decode, TardisFlyPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }
}
package org.theplaceholder.tardisfly.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.theplaceholder.tardisfly.TardisFly;

@Mod.EventBusSubscriber(modid = TardisFly.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientTick() {
    }
}

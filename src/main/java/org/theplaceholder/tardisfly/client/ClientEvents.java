package org.theplaceholder.tardisfly.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.theplaceholder.tardisfly.TardisFly;
import org.theplaceholder.tardisfly.utils.ExteriorsIDs;

@Mod.EventBusSubscriber(modid = TardisFly.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onLivingRender(RenderLivingEvent.Pre event) {
        if (ClientVars.playerExteriorMap.containsKey(event.getEntity().getUUID().toString())) {
            event.setCanceled(true);

            int i = ClientVars.playerExteriorMap.get(event.getEntity().getUUID().toString());

            ExteriorsIDs.render(event.getEntity().getUUID().toString(), event.getMatrixStack(), event.getBuffers(), event.getLight(), event.getPartialRenderTick(), i, event.getEntity().isOnGround());
        }
    }
}

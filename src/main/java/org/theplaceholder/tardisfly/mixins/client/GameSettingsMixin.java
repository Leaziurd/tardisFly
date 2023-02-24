package org.theplaceholder.tardisfly.mixins.client;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.theplaceholder.tardisfly.client.ClientVars;

@Mixin(value = GameSettings.class)
public class GameSettingsMixin {

    @Inject(at = @At("HEAD"), method = "setCameraType", cancellable = true)
    public void setCameraType(PointOfView p_243229_1_, CallbackInfo ci) {
        if (ClientVars.playerExteriorMap.containsKey(Minecraft.getInstance().player.getUUID().toString()) && p_243229_1_ != PointOfView.THIRD_PERSON_BACK)
            ci.cancel();
    }
}

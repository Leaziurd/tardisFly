package org.theplaceholder.tardisfly.mixins;

import net.minecraft.entity.player.PlayerEntity;
import net.tardis.mod.controls.AbstractControl;
import net.tardis.mod.tileentities.ConsoleTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.cap.tfly.TardisFlyCapability;

@Mixin(value = AbstractControl.class, remap = false)
public class AbstractControlMixin {

    @Inject(at = @At("HEAD"), method = "onRightClicked", cancellable = true)
    private void onRightClicked(ConsoleTile console, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        TardisFlyCapability cap = (TardisFlyCapability) console.getLevel().getCapability(Capabilities.TARDIS_FLY).orElse(null);
        if(cap.getFlyingPlayerUUID().equals("0"))
            cir.setReturnValue(false);
    }
}

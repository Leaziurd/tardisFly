package org.theplaceholder.tardisfly.mixins;

import net.minecraft.entity.player.PlayerEntity;
import net.tardis.mod.cap.TardisWorldCapability;
import net.tardis.mod.controls.AbstractControl;
import net.tardis.mod.tileentities.ConsoleTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AbstractControl.class, remap = false)
public class AbstractControlMixin {

    @Inject(at = @At("HEAD"), method = "onHit", cancellable = true)
    private void onHit(ConsoleTile console, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        cancelIfFlying(console, cir);
    }

    @Inject(at = @At("HEAD"), method = "onRightClicked", cancellable = true)
    private void onRightClicked(ConsoleTile console, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        cancelIfFlying(console, cir);
    }

    private void cancelIfFlying(ConsoleTile console, CallbackInfoReturnable<Boolean> cir) {
        if (true /* add check if a player is flying the tardis*/){
            // player is flying tardis message
            cir.setReturnValue(false);
        }
    }
}

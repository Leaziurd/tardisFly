package org.theplaceholder.tardisfly.mixins;

import net.minecraft.entity.player.PlayerEntity;
import net.tardis.mod.controls.AbstractControl;
import net.tardis.mod.tileentities.ConsoleTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.theplaceholder.tardisfly.cap.Capabilities;

import java.util.Objects;

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
        console.getLevel().getCapability(Capabilities.TARDIS_FLY).ifPresent((cap) -> {
            if (cap.getFlyingPlayerUUID() != null && !Objects.equals(cap.getFlyingPlayerUUID(), "0")) {
                cir.setReturnValue(true);
            }
        });
    }
}

package org.theplaceholder.tardisfly.mixins.tardis;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.management.PlayerList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.cap.tfly.TardisFlyCapability;
import org.theplaceholder.tardisfly.interfaces.ExteriorTileMixinInterface;

import java.util.UUID;

@Mixin(value = ExteriorTile.class, remap = false)
public abstract class ExteriorTileMixin extends TileEntity implements ExteriorTileMixinInterface {
    @Shadow protected RegistryKey<World> interiorDimension;

    @Shadow public abstract void placeExteriorBlocks();

    public ExteriorTileMixin(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    private float rot = 0;


    @Inject(at = @At("HEAD"), method = "func_73660_a")
    private void tick(CallbackInfo ci) {
        this.handleTardisFly();
    }

    private void handleTardisFly() {
        if (!this.level.isClientSide) {
            TardisFlyCapability cap = (TardisFlyCapability) this.level.getServer().getLevel(this.interiorDimension).getCapability(Capabilities.TARDIS_FLY).orElse(null);
            if (cap != null){
                if (cap.getFlyingPlayerUUID().equals("0"))
                    return;
                PlayerEntity player = this.level.getServer().getPlayerList().getPlayer(UUID.fromString(cap.getFlyingPlayerUUID()));
                if (player != null) {
                    if (!player.isOnGround()){
                        if (this.rot > 360) {
                            this.rot = 0;
                        }
                        this.rot += 1.5f;
                    }
                }
            }
        }
    }

    @Override
    public float getRot() {
        return this.rot;
    }
}

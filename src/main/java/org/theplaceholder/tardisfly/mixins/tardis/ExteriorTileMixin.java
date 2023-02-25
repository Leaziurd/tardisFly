package org.theplaceholder.tardisfly.mixins.tardis;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;
import org.spongepowered.asm.mixin.Mixin;
import org.theplaceholder.tardisfly.interfaces.ExteriorTileMixinInterface;

@Mixin(value = ExteriorTile.class, remap = false)
public abstract class ExteriorTileMixin extends TileEntity implements ExteriorTileMixinInterface {

    public ExteriorTileMixin(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    private float rot = 0;

    @Override
    public float getRot() {
        return this.rot;
    }

    @Override
    public void setRot(float rot) {
        this.rot = rot;
    }
}

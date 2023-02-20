package org.theplaceholder.tardisfly.cap.tfly;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.theplaceholder.tardisfly.cap.Capabilities;

public interface ITardisFly extends INBTSerializable<CompoundNBT> {
    String getFlyingPlayerUUID();
    void setFlyingPlayerUUID(String flyingPlayerUUID);

    class Provider implements ICapabilitySerializable<CompoundNBT> {
        final ITardisFly tardisFly;
        final LazyOptional<ITardisFly> provide;

        public Provider(ITardisFly tardisFly) {
            this.tardisFly = tardisFly;
            this.provide = LazyOptional.of(() -> {
                return this.tardisFly;
            });
        }

        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == Capabilities.TARDIS_FLY ? this.provide.cast() : LazyOptional.empty();
        }

        public CompoundNBT serializeNBT() {
            return (CompoundNBT)this.tardisFly.serializeNBT();
        }

        public void deserializeNBT(CompoundNBT nbt) {
            this.tardisFly.deserializeNBT(nbt);
        }
    }
}

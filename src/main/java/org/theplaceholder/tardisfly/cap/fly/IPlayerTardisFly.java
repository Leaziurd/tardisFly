package org.theplaceholder.tardisfly.cap.fly;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.theplaceholder.tardisfly.cap.Capabilities;

public interface IPlayerTardisFly extends INBTSerializable<CompoundNBT> {
    String getTardisID();
    void setTardisID(String tardisID);

    int getTardisX();
    void setTardisX(int tardisX);
    int getTardisY();
    void setTardisY(int tardisY);
    int getTardisZ();
    void setTardisZ(int tardisZ);

    int getTardisYaw();
    void setTardisYaw(int tardisYaw);
    int getTardisPitch();
    void setTardisPitch(int tardisPitch);

    class Provider implements ICapabilitySerializable<CompoundNBT> {
        final IPlayerTardisFly playerTardisFly;
        final LazyOptional<IPlayerTardisFly> provide;

        public Provider(IPlayerTardisFly playerTardisFly) {
            this.playerTardisFly = playerTardisFly;
            this.provide = LazyOptional.of(() -> {
                return this.playerTardisFly;
            });
        }

        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == Capabilities.PLAYER_TARDIS_FLY ? this.provide.cast() : LazyOptional.empty();
        }

        public CompoundNBT serializeNBT() {
            return (CompoundNBT)this.playerTardisFly.serializeNBT();
        }

        public void deserializeNBT(CompoundNBT nbt) {
            this.playerTardisFly.deserializeNBT(nbt);
        }
    }
}

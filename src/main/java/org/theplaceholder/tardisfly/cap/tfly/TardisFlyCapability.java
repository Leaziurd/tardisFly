package org.theplaceholder.tardisfly.cap.tfly;

import net.minecraft.nbt.CompoundNBT;

public class TardisFlyCapability implements ITardisFly{

    private String flyingPlayerUUID = "0";

    @Override
    public String getFlyingPlayerUUID() {
        return flyingPlayerUUID;
    }

    @Override
    public void setFlyingPlayerUUID(String flyingPlayerUUID) {
        this.flyingPlayerUUID = flyingPlayerUUID;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("flyingPlayerUUID", flyingPlayerUUID);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        flyingPlayerUUID = nbt.getString("flyingPlayerUUID");
    }
}

package org.theplaceholder.tardisfly.cap.fly;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public class PlayerTardisFlyCapability implements IPlayerTardisFly {
    private String tardisID;

    private int tardisX;
    private int tardisY;
    private int tardisZ;
    private int tardisYaw;
    private int tardisPitch;

    @Override
    public String getTardisID() {
        return tardisID;
    }

    public void setTardisXYZFromBlockPos(BlockPos pos) {
        setTardisX(pos.getX());
        setTardisY(pos.getY());
        setTardisZ(pos.getZ());
    }

    @Override
    public int getTardisX() {
        return tardisX;
    }

    @Override
    public void setTardisX(int tardisX) {
        this.tardisX = tardisX;
    }

    @Override
    public int getTardisY() {
        return tardisY;
    }

    @Override
    public void setTardisY(int tardisY) {
        this.tardisY = tardisY;
    }

    @Override
    public int getTardisZ() {
        return tardisZ;
    }

    @Override
    public void setTardisZ(int tardisZ) {
        this.tardisZ = tardisZ;
    }

    @Override
    public int getTardisYaw() {
        return tardisYaw;
    }

    @Override
    public void setTardisYaw(int tardisYaw) {
        this.tardisYaw = tardisYaw;
    }

    @Override
    public int getTardisPitch() {
        return tardisPitch;
    }

    @Override
    public void setTardisPitch(int tardisPitch) {
        this.tardisPitch = tardisPitch;
    }


    @Override
    public void setTardisID(String tardisID) {
        this.tardisID = tardisID;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("tardisID", getTardisID());

        nbt.putInt("tardisX", getTardisX());
        nbt.putInt("tardisY", getTardisY());
        nbt.putInt("tardisZ", getTardisZ());

        nbt.putInt("tardisYaw", getTardisYaw());
        nbt.putInt("tardisPitch", getTardisPitch());

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        tardisID = nbt.getString("tardisID");

        tardisX = nbt.getInt("tardisX");
        tardisY = nbt.getInt("tardisY");
        tardisZ = nbt.getInt("tardisZ");

        tardisYaw = nbt.getInt("tardisYaw");
        tardisPitch = nbt.getInt("tardisPitch");
    }
}

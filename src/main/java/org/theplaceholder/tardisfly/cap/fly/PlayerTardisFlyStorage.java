package org.theplaceholder.tardisfly.cap.fly;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerTardisFlyStorage implements Capability.IStorage<IPlayerTardisFly> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IPlayerTardisFly> capability, IPlayerTardisFly instance, Direction side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IPlayerTardisFly> capability, IPlayerTardisFly instance, Direction side, INBT nbt) {
        instance.deserializeNBT((CompoundNBT) nbt);
    }
}

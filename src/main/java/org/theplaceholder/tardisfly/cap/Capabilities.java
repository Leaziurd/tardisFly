package org.theplaceholder.tardisfly.cap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import org.theplaceholder.tardisfly.cap.fly.IPlayerTardisFly;
import org.theplaceholder.tardisfly.cap.fly.PlayerTardisFlyCapability;
import org.theplaceholder.tardisfly.cap.tfly.ITardisFly;
import org.theplaceholder.tardisfly.cap.tfly.TardisFlyCapability;

public class Capabilities {
    @CapabilityInject(IPlayerTardisFly.class)
    public static Capability<IPlayerTardisFly> PLAYER_TARDIS_FLY = null;

    @CapabilityInject(ITardisFly.class)
    public static Capability<ITardisFly> TARDIS_FLY = null;

    public static IPlayerTardisFly getPlayerTardisFlyCapability(PlayerEntity player) {
        return player.getCapability(PLAYER_TARDIS_FLY).orElse(new PlayerTardisFlyCapability());
    }
}

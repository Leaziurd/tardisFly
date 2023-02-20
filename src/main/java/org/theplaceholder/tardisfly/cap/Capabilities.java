package org.theplaceholder.tardisfly.cap;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import org.theplaceholder.tardisfly.cap.fly.IPlayerTardisFly;

public class Capabilities {
    @CapabilityInject(IPlayerTardisFly.class)
    public static Capability<IPlayerTardisFly> PLAYER_TARDIS_FLY = null;
}

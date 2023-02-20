package org.theplaceholder.tardisfly.cap;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import org.theplaceholder.tardisfly.cap.fly.IPlayerTardisFly;
import org.theplaceholder.tardisfly.cap.tfly.ITardisFly;

public class Capabilities {
    @CapabilityInject(IPlayerTardisFly.class)
    public static Capability<IPlayerTardisFly> PLAYER_TARDIS_FLY = null;

    @CapabilityInject(ITardisFly.class)
    public static Capability<ITardisFly> TARDIS_FLY = null;
}

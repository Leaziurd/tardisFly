package org.theplaceholder.tardisfly;

import net.tardis.mod.tileentities.ConsoleTile;
import org.theplaceholder.tardisfly.cap.Capabilities;

import java.util.Objects;

public class FlyUtils {
    public static boolean isFlying(ConsoleTile console) {
        String id = console.getLevel().getCapability(Capabilities.TARDIS_FLY).orElse(null).getFlyingPlayerUUID();
        return id != null && !Objects.equals(id, "0");
    }
}

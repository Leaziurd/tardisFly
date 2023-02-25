package org.theplaceholder.tardisfly.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientVars {
    public static Map<String, Integer> playerExteriorMap = new HashMap<>();
    public static Map<String, Float> playerRotationMap = new HashMap<>();
}

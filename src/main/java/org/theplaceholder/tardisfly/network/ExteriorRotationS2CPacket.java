package org.theplaceholder.tardisfly.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.theplaceholder.tardisfly.client.ClientVars;

import java.util.function.Supplier;

public class ExteriorRotationS2CPacket {
    private float rot;
    private String uuid;

    public ExteriorRotationS2CPacket(String uuid, float rot) {
        this.uuid = uuid;
        this.rot = rot;
    }

    public static void encode(ExteriorRotationS2CPacket packet, PacketBuffer packetBuffer) {
        packetBuffer.writeUtf(packet.uuid);
        packetBuffer.writeFloat(packet.rot);
    }

    public static ExteriorRotationS2CPacket decode(PacketBuffer packetBuffer) {
        String uuid = packetBuffer.readUtf();
        float rot = packetBuffer.readFloat();
        return new ExteriorRotationS2CPacket(uuid, rot);
    }

    public static void handle(ExteriorRotationS2CPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ClientVars.playerRotationMap.put(msg.uuid, msg.rot);
        });
    }
}

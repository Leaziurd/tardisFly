package org.theplaceholder.tardisfly.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.theplaceholder.tardisfly.client.ClientVars;

import java.util.function.Supplier;

public class ExteriorRotationPacket {
    private float rot;
    private String uuid;

    public ExteriorRotationPacket(String uuid, float rot) {
        this.uuid = uuid;
        this.rot = rot;
    }

    public static void encode(ExteriorRotationPacket packet, PacketBuffer packetBuffer) {
        packetBuffer.writeUtf(packet.uuid);
        packetBuffer.writeFloat(packet.rot);
    }

    public static ExteriorRotationPacket decode(PacketBuffer packetBuffer) {
        String uuid = packetBuffer.readUtf();
        float rot = packetBuffer.readFloat();
        return new ExteriorRotationPacket(uuid, rot);
    }

    public static void handle(ExteriorRotationPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ClientVars.playerRotationMap.remove(msg.uuid);
            ClientVars.playerRotationMap.put(msg.uuid, msg.rot);
            ClientVars.rotationMapLastUpdated = 0;
        });
    }
}

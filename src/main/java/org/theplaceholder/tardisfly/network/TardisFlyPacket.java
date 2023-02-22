package org.theplaceholder.tardisfly.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.tileentities.ConsoleTile;
import org.theplaceholder.tardisfly.client.ClientVars;

import java.util.UUID;
import java.util.function.Supplier;

public class TardisFlyPacket {
    private String uuid;
    private int exterior;

    public ConsoleTile consoleTile;

    public TardisFlyPacket(UUID uuid, int exterior) {
        this(uuid.toString(), exterior);
    }

    public TardisFlyPacket(String uuid, int exterior) {
        this.uuid = uuid;
        this.exterior = exterior;
    }

    public static void encode(TardisFlyPacket packet, PacketBuffer packetBuffer) {
        packetBuffer.writeUtf(packet.uuid);
        packetBuffer.writeInt(packet.exterior);
    }

    public static TardisFlyPacket decode(PacketBuffer packetBuffer) {
        String uuid = packetBuffer.readUtf();
        int exterior = packetBuffer.readInt();
        return new TardisFlyPacket(uuid, exterior);
    }

    public static void handle(TardisFlyPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ClientVars.playerExteriorMap.put(msg.uuid, msg.exterior);
        });
    }
}

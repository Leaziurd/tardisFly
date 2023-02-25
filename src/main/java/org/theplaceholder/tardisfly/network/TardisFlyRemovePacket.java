package org.theplaceholder.tardisfly.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.tardis.mod.tileentities.ConsoleTile;
import org.theplaceholder.tardisfly.TardisFly;
import org.theplaceholder.tardisfly.client.ClientVars;

import java.util.UUID;
import java.util.function.Supplier;

public class TardisFlyRemovePacket {
    private String uuid;

    public TardisFlyRemovePacket(UUID uuid) {
        this(uuid.toString());
    }

    public TardisFlyRemovePacket(String uuid) {
        this.uuid = uuid;
    }

    public static void encode(TardisFlyRemovePacket packet, PacketBuffer packetBuffer) {
        packetBuffer.writeUtf(packet.uuid);
    }

    public static TardisFlyRemovePacket decode(PacketBuffer packetBuffer) {
        String uuid = packetBuffer.readUtf();
        return new TardisFlyRemovePacket(uuid);
    }

    public static void handle(TardisFlyRemovePacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ClientVars.playerExteriorMap.remove(msg.uuid);
            TardisFly.NETWORK.sendToServer(new ExteriorRotationC2SPacket(msg.uuid, ClientVars.playerRotationMap.get(msg.uuid)));
        });
    }
}

package org.theplaceholder.tardisfly.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.tileentities.ConsoleTile;
import org.theplaceholder.tardisfly.TardisFly;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.interfaces.ExteriorTileMixinInterface;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public class ExteriorRotationC2SPacket {
    public float rot;
    public String uuid;

    public ExteriorRotationC2SPacket(String uuid, float rot) {
        this.uuid = uuid;
        this.rot = rot;
    }

    public static void encode(ExteriorRotationC2SPacket packet, PacketBuffer packetBuffer) {
        packetBuffer.writeUtf(packet.uuid);
        packetBuffer.writeFloat(packet.rot);
    }

    public static ExteriorRotationC2SPacket decode(PacketBuffer packetBuffer) {
        String uuid = packetBuffer.readUtf();
        float rot = packetBuffer.readFloat();
        return new ExteriorRotationC2SPacket(uuid, rot);
    }

    public static void handle(ExteriorRotationC2SPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getSender() != null)
                context.get().getSender().server.getPlayerList().getPlayer(UUID.fromString(msg.uuid)).getCapability(Capabilities.PLAYER_TARDIS_FLY).ifPresent(playerTardisFly -> {
                    if (playerTardisFly.getTardisID() != null || !Objects.equals(playerTardisFly.getTardisID(), "0")) return;
                    World tWorld = Objects.requireNonNull(context.get().getSender()).server.getLevel(WorldHelper.getWorldKeyFromRL(new ResourceLocation("tardis", playerTardisFly.getTardisID())));
                    ConsoleTile consoleTile = TardisHelper.getConsoleInWorld(tWorld).get();
                    ((ExteriorTileMixinInterface) consoleTile.getOrFindExteriorTile()).setRot(msg.rot);
                });
        });
    }
}

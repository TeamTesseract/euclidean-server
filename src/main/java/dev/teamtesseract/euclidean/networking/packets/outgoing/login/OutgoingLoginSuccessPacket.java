package dev.teamtesseract.euclidean.networking.packets.outgoing.login;

import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.packets.Packet;

import java.util.UUID;

public class OutgoingLoginSuccessPacket implements Packet.Outgoing {

    private final UUID playerUuid;
    private final String playerName;

    public OutgoingLoginSuccessPacket(UUID playerUuid, String playerName) {
        this.playerUuid = playerUuid;
        this.playerName = playerName;
    }

    @Override
    public int getPacketId() {
        return 0x02;
    }

    @Override
    public void writePacketData(PacketBuffer buffer) {
        buffer.writeUuid(playerUuid).writeString(playerName);
    }
}

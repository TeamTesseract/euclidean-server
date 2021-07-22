package dev.teamtesseract.euclidean.networking.packets.outgoing.login;

import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class OutgoingDisconnectPacket implements Packet.Outgoing {

    private final String disconnectReason;

    public OutgoingDisconnectPacket(String disconnectReason) {
        this.disconnectReason = disconnectReason;
    }

    @Override
    public int getPacketId() {
        return 0x00;
    }

    @Override
    public void writePacketData(PacketBuffer buffer) {
        buffer.writeString(disconnectReason);
    }
}

package dev.teamtesseract.euclidean.networking.packets.outgoing.status;

import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public record OutgoingPingResponsePacket(long payload) implements Packet.Outgoing {

    @Override
    public int getPacketId() {
        return 0x01;
    }

    @Override
    public void writePacketData(PacketBuffer buffer) {
        buffer.writeLong(payload);
    }
}

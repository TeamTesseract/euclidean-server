package dev.teamtesseract.euclidean.networking.packets.outgoing.login;

import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class OutgoingSetCompressionPacket implements Packet.Outgoing {

    private final int threshold;

    public OutgoingSetCompressionPacket(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public int getPacketId() {
        return 0x03;
    }

    @Override
    public void writePacketData(PacketBuffer buffer) {
        buffer.writeVarInt(threshold);
    }
}

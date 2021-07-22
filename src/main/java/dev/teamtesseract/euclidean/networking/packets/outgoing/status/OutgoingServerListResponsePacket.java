package dev.teamtesseract.euclidean.networking.packets.outgoing.status;

import dev.teamtesseract.euclidean.metadata.ServerListProperties;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public record OutgoingServerListResponsePacket(ServerListProperties properties) implements Packet.Outgoing {

    @Override
    public int getPacketId() {
        return 0x00;
    }

    @Override
    public void writePacketData(PacketBuffer buffer) {
        buffer.writeString(properties.getPingResponse().toString());
    }
}

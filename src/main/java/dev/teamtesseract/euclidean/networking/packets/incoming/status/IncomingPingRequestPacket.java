package dev.teamtesseract.euclidean.networking.packets.incoming.status;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;
import dev.teamtesseract.euclidean.networking.packets.Packet;
import dev.teamtesseract.euclidean.networking.packets.outgoing.status.OutgoingPingResponsePacket;

public class IncomingPingRequestPacket implements Packet.Incoming {

    private long payload;

    @Override
    public int getPacketId() {
        return 0x01;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        this.payload = buffer.readLong();
    }

    @Override
    public void handle(EuclideanServer server, PlayerConnection connection) {
        connection.sendPacket(new OutgoingPingResponsePacket(payload));
        connection.disconnect();
    }
}

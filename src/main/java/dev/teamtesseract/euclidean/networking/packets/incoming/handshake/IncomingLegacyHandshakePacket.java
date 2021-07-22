package dev.teamtesseract.euclidean.networking.packets.incoming.handshake;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class IncomingLegacyHandshakePacket implements Packet.Incoming {

    // Arguments literally don't matter, it should only ever give us a 0x01, and therefor request a list ping.
    // If you handshake with a legacy packet and want anything else but a list ping, tough luck, enjoy invalid data.

    @Override
    public int getPacketId() {
        return 0xFE;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) { }

    @Override
    public void handle(EuclideanServer server, PlayerConnection connection) {
        //TODO this
    }
}

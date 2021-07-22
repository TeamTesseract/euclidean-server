package dev.teamtesseract.euclidean.networking.packets.incoming.login;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class IncomingEncryptionResponsePacket implements Packet.Incoming {

    private byte[] sharedSecret, verifyToken;

    @Override
    public int getPacketId() {
        return 0x01;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        this.sharedSecret = buffer.readByteArray();
        this.verifyToken = buffer.readByteArray();
    }

    @Override
    public void handle(EuclideanServer server, PlayerConnection connection) {

    }
}

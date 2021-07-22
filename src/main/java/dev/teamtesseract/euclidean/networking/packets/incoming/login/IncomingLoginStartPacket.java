package dev.teamtesseract.euclidean.networking.packets.incoming.login;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class IncomingLoginStartPacket implements Packet.Incoming {

    private String playerName;

    @Override
    public int getPacketId() {
        return 0x00;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        this.playerName = buffer.readString();
    }

    @Override
    public void handle(EuclideanServer server, PlayerConnection connection) {

    }
}

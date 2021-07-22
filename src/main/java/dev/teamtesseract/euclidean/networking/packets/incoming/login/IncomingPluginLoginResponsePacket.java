package dev.teamtesseract.euclidean.networking.packets.incoming.login;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class IncomingPluginLoginResponsePacket implements Packet.Incoming {

    private int uniqueConnectionId;
    private boolean success;
    private byte[] data;

    @Override
    public int getPacketId() {
        return 0x02;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        this.uniqueConnectionId = buffer.readVarInt();
        this.success = buffer.readBoolean();
        if(success)
            this.data = buffer.readRemainingData();
        else
            this.data = new byte[0];
    }

    @Override
    public void handle(EuclideanServer server, PlayerConnection connection) {

    }
}

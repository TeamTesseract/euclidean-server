package dev.teamtesseract.euclidean.networking.packets.incoming.handshake;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.ConnectionState;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class IncomingHandshakePacket implements Packet.Incoming {

    private int protocolVersion;
    private String serverAddress;
    private int serverPort;
    private HandshakeState nextState;

    @Override
    public int getPacketId() {
        return 0x00;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        this.protocolVersion = buffer.readVarInt();
        this.serverAddress = buffer.readString();
        this.serverPort = buffer.readShort(); //TODO Should be unsigned
        this.nextState = HandshakeState.fromId(buffer.readVarInt());
    }

    @Override
    public void handle(EuclideanServer server, PlayerConnection connection) {
        switch(nextState) {
            case STATUS -> connection.updateConnectionState(ConnectionState.STATUS);
            case LOGIN -> { /*TODO This*/ }
        }
    }

    public enum HandshakeState {
        STATUS(1),
        LOGIN(2);

        private final int id;

        HandshakeState(int id) {
            this.id = id;
        }

        public static HandshakeState fromId(int id) {
            if(id <= 1)
                return STATUS;
            else
                return LOGIN;
        }

        public int toId() {
            return this.id;
        }
    }
}

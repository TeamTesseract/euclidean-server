package dev.teamtesseract.euclidean.networking.packets.incoming.status;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.metadata.ServerListProperties;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;
import dev.teamtesseract.euclidean.networking.packets.Packet;
import dev.teamtesseract.euclidean.networking.packets.outgoing.status.OutgoingServerListResponsePacket;

public class IncomingServerListRequestPacket implements Packet.Incoming {

    // No arguments, only triggers the list ping progress.

    @Override
    public int getPacketId() {
        return 0x00;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) { }

    @Override
    public void handle(EuclideanServer server, PlayerConnection connection) {
        ServerListProperties properties = new ServerListProperties(new ServerListProperties.PlayerInfo(3, 1, "ItIsI"), "Euclidean says Hello World!");
        connection.sendPacket(new OutgoingServerListResponsePacket(properties));
    }
}

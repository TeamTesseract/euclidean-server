package dev.teamtesseract.euclidean.networking.packets;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;

public interface Packet {
    int getPacketId();

    interface Outgoing extends Packet {
        void writePacketData(PacketBuffer buffer);
    }

    interface Incoming extends Packet {
        void readPacketData(PacketBuffer buffer);
        void handle(EuclideanServer server, PlayerConnection connection);
    }
}

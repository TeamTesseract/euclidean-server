package dev.teamtesseract.euclidean.networking;

import dev.teamtesseract.euclidean.networking.packethandlers.HandshakePacketHandler;
import dev.teamtesseract.euclidean.networking.packethandlers.PacketHandler;
import dev.teamtesseract.euclidean.networking.packethandlers.StatusPacketHandler;

public enum ConnectionState {
    HANDSHAKING(new HandshakePacketHandler()),
    STATUS(new StatusPacketHandler()),
    LOGIN(null),
    PLAY(null);

    private final PacketHandler packetHandler;

    ConnectionState(PacketHandler packetHandler) {
        this.packetHandler = packetHandler;
    }

    public PacketHandler getPacketHandler() {
        return packetHandler;
    }
}

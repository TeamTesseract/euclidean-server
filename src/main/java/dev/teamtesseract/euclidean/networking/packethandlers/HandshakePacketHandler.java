package dev.teamtesseract.euclidean.networking.packethandlers;

import dev.teamtesseract.euclidean.networking.packets.incoming.handshake.IncomingHandshakePacket;
import dev.teamtesseract.euclidean.networking.packets.incoming.handshake.IncomingLegacyHandshakePacket;

public class HandshakePacketHandler extends PacketHandler {

    public HandshakePacketHandler() {
        super("Handshaking");
        registerPacket(IncomingHandshakePacket::new);
        registerPacket(IncomingLegacyHandshakePacket::new);
    }
}

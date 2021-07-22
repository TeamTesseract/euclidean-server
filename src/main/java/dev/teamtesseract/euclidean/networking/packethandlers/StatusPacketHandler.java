package dev.teamtesseract.euclidean.networking.packethandlers;

import dev.teamtesseract.euclidean.networking.packets.incoming.status.IncomingPingRequestPacket;
import dev.teamtesseract.euclidean.networking.packets.incoming.status.IncomingServerListRequestPacket;

public class StatusPacketHandler extends PacketHandler {

    public StatusPacketHandler() {
        super("Status");
        registerPacket(IncomingServerListRequestPacket::new);
        registerPacket(IncomingPingRequestPacket::new);
    }
}

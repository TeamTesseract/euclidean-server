package dev.teamtesseract.euclidean.networking.packets.outgoing.login;

import dev.teamtesseract.euclidean.metadata.Identifier;
import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class OutgoingPluginLoginRequestPacket implements Packet.Outgoing {

    private final int uniqueConnectionId;
    private final Identifier channel;
    private final byte[] customData;

    public OutgoingPluginLoginRequestPacket(int uniqueConnectionId, Identifier channel, byte[]customData) {
        this.uniqueConnectionId = uniqueConnectionId;
        this.channel = channel;
        this.customData = customData;
    }

    @Override
    public int getPacketId() {
        return 0x04;
    }

    @Override
    public void writePacketData(PacketBuffer buffer) {
        buffer.writeVarInt(uniqueConnectionId)
                .writeIdentifier(channel)
                .writeByteArray(customData);
    }
}

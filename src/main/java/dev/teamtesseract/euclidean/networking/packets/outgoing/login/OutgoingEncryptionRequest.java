package dev.teamtesseract.euclidean.networking.packets.outgoing.login;

import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.packets.Packet;

public class OutgoingEncryptionRequest implements Packet.Outgoing {

    private final byte[] publicKey, verifyToken;

    public OutgoingEncryptionRequest(byte[] publicKey, byte[] verifyToken) {
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    @Override
    public int getPacketId() {
        return 0x01;
    }

    @Override
    public void writePacketData(PacketBuffer buffer) {
        buffer.writeString("")
                .writeLengthByteArray(publicKey)
                .writeLengthByteArray(verifyToken);
    }
}

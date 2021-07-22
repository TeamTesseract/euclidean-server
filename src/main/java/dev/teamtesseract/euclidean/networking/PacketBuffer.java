package dev.teamtesseract.euclidean.networking;

import dev.teamtesseract.euclidean.metadata.Identifier;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public record PacketBuffer(ByteBuf nettyBuffer) {

    private static final int MAX_STRING_LENGTH = 32767;

    public boolean readBoolean() {
        return nettyBuffer.readBoolean();
    }
    public PacketBuffer writeBoolean(boolean b) {
        nettyBuffer.writeBoolean(b);
        return this;
    }

    public byte readByte() {
        return nettyBuffer.readByte();
    }
    public PacketBuffer writeByte(byte b) {
        nettyBuffer.writeByte(b);
        return this;
    }

    public byte readUnsignedByte() {
        byte b = readByte();
        if(b < 0)
            return (byte) (b + 128);
        else
            return b;
    }
    public PacketBuffer writeUnsignedByte(byte b) {
        if(b < 0)
            nettyBuffer.writeByte(b + 128);
        else
            nettyBuffer.writeByte(b);
        return this;
    }

    public short readShort() {
        return nettyBuffer.readShort();
    }
    public PacketBuffer writeShort(short s) {
        nettyBuffer.writeShort(s);
        return this;
    }

    //TODO Unsigned short

    public int readInt() {
        return nettyBuffer.readInt();
    }
    public PacketBuffer writeInt(int i) {
        nettyBuffer.writeInt(i);
        return this;
    }

    public long readLong() {
        return nettyBuffer.readLong();
    }
    public PacketBuffer writeLong(long l) {
        nettyBuffer.writeLong(l);
        return this;
    }

    public float readFloat() {
        return nettyBuffer.readFloat();
    }
    public PacketBuffer writeFloat(float f) {
        nettyBuffer.writeFloat(f);
        return this;
    }

    public double readDouble() {
        return nettyBuffer.readDouble();
    }
    public PacketBuffer writeDouble(double d) {
        nettyBuffer.writeDouble(d);
        return this;
    }

    public String readString() {
        int length = readVarInt();
        if(length > MAX_STRING_LENGTH)
            throw new RuntimeException("String exceeds maximum allowed String length! [" + length + " > " + MAX_STRING_LENGTH + "]");
        return nettyBuffer.readCharSequence(length, StandardCharsets.UTF_8).toString();
    }
    public PacketBuffer writeString(String s) {
        if(s.length() > MAX_STRING_LENGTH)
            throw new RuntimeException("String exceeds maximum allowed String length! [" + s.length() + " > " + MAX_STRING_LENGTH + "]");
        writeVarInt(s.length());
        nettyBuffer.writeCharSequence(s, StandardCharsets.UTF_8);
        return this;
    }

    public Identifier readIdentifier() {
        return Identifier.of(readString());
    }
    public PacketBuffer writeIdentifier(Identifier id) {
        writeString(id.toString());
        return this;
    }

    public int readVarInt() {
        int decoded = 0;
        int bitOffset = 0;
        byte current;

        do {
            current = readByte();
            decoded |= (current & 0b01111111) << bitOffset;

            bitOffset += 7;
        } while((current & 0b10000000) != 0);

        return decoded;
    }
    public PacketBuffer writeVarInt(int i) {
        do {
            byte current = (byte) (i & 0b01111111);
            i >>>= 7;
            if(i != 0)
                current |= 0b10000000;

            writeByte(current);
        } while(i != 0);
        return this;
    }

    public UUID readUuid() {
        return new UUID(readLong(), readLong());
    }
    public PacketBuffer writeUuid(UUID uuid) {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
        return this;
    }

    public byte[] readByteArray() {
        byte[] data = new byte[readVarInt()];
        nettyBuffer().readBytes(data);
        return data;
    }
    public byte[] readRemainingData() {
        return nettyBuffer.readBytes(nettyBuffer.readableBytes()).array();
    }
    public PacketBuffer writeByteArray(byte[] array) {
        nettyBuffer.writeBytes(array);
        return this;
    }
    public PacketBuffer writeLengthByteArray(byte[] bytes) {
        writeVarInt(bytes.length);
        writeByteArray(bytes);
        return this;
    }
    public PacketBuffer writeByteBuf(ByteBuf msg, int readerIndex, int packetSize) {
        nettyBuffer.writeBytes(msg, readerIndex, packetSize);
        return this;
    }

    public PacketBuffer ensureWritable(int size) {
        nettyBuffer.ensureWritable(size);
        return this;
    }

    public static int getVarIntLength(int i) {
        for(int j = 1; j < 5; ++j)
            if ((i & -1 << j * 7) == 0)
                return j;
        return 5;
    }
}

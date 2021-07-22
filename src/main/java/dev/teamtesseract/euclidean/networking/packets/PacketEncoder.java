package dev.teamtesseract.euclidean.networking.packets;

import dev.teamtesseract.euclidean.networking.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class PacketEncoder extends MessageToByteEncoder<Packet.Outgoing> {

    protected void encode(ChannelHandlerContext ctx, Packet.Outgoing msg, ByteBuf out) throws Exception {
        msg.writePacketData(new PacketBuffer(out).writeVarInt(msg.getPacketId()));
    }

    public static class PacketPrepender extends MessageToByteEncoder<ByteBuf> {

        protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
            int packetSize = msg.readableBytes();
            new PacketBuffer(out).ensureWritable(packetSize + PacketBuffer.getVarIntLength(packetSize))
                    .writeVarInt(packetSize)
                    .writeByteBuf(msg, msg.readerIndex(), packetSize);
        }
    }
}

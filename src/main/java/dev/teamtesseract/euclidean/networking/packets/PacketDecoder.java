package dev.teamtesseract.euclidean.networking.packets;

import dev.teamtesseract.euclidean.networking.PacketBuffer;
import dev.teamtesseract.euclidean.networking.PlayerConnection;
import dev.teamtesseract.euclidean.networking.packethandlers.PacketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() > 0) {
            PacketBuffer buffer = new PacketBuffer(in);
            PacketHandler handler = ctx.channel().attr(PlayerConnection.ATTRIBUTE_KEY_PROTOCOL).get().getPacketHandler();
            Packet.Incoming packet = handler.getPacketFromId(buffer.readVarInt());
            packet.readPacketData(buffer);
            out.add(packet);
        }
    }
}

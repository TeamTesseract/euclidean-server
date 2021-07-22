package dev.teamtesseract.euclidean.networking;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.packets.Packet;
import io.netty.channel.*;
import io.netty.util.AttributeKey;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PlayerConnection extends SimpleChannelInboundHandler<Packet.Incoming> {

    public static final AttributeKey<ConnectionState> ATTRIBUTE_KEY_PROTOCOL = AttributeKey.newInstance("protocol");

    private final Queue<Packet.Outgoing> packetQueue = new ConcurrentLinkedQueue<>();
    private final EuclideanServer server;

    private Channel nettyChannel;

    public PlayerConnection(EuclideanServer server) {
        this.server = server;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.nettyChannel = ctx.channel();
        updateConnectionState(ConnectionState.HANDSHAKING);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet.Incoming msg) {
        if(isChannelOpen())
            msg.handle(server, this);
    }

    /*public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) {
        if (throwable instanceof UnknownPacketException) {
            System.out.println("Packet Error: " + throwable.getMessage());
        }
    }*/

    public void sendPacket(Packet.Outgoing packet) {
        if(isChannelOpen()) {
            sendPacketQueue();
            sendPacketNow(packet);
        } else {
            packetQueue.add(packet);
        }
    }

    public void sendPacketNow(Packet.Outgoing packet) {
        if(this.nettyChannel.eventLoop().inEventLoop()) {
            ChannelFuture future = this.nettyChannel.writeAndFlush(packet);
            future.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        } else {
            this.nettyChannel.eventLoop().execute(() -> {
                ChannelFuture future = this.nettyChannel.writeAndFlush(packet);
                future.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            });
        }
    }

    public void updateConnectionState(ConnectionState state) {
        this.nettyChannel.attr(ATTRIBUTE_KEY_PROTOCOL).set(state);
        this.nettyChannel.config().setAutoRead(true);
        System.out.println("Updated Connection State to: " + state);
    }

    public void disconnect() {
        if(nettyChannel.isOpen())
            nettyChannel.close().awaitUninterruptibly();
    }

    public boolean isChannelOpen() {
        return nettyChannel != null && nettyChannel.isOpen();
    }

    private void sendPacketQueue() {
        if(isChannelOpen()) {
            synchronized(packetQueue) {
                Packet.Outgoing packet;
                while((packet = packetQueue.poll()) != null)
                    sendPacketNow(packet);
            }
        }
    }
}

package dev.teamtesseract.euclidean.networking;

import dev.teamtesseract.euclidean.EuclideanServer;
import dev.teamtesseract.euclidean.networking.packets.PacketDecoder;
import dev.teamtesseract.euclidean.networking.packets.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServerBinding {

    private static final NioEventLoopGroup DEFAULT_CHANNEL = new NioEventLoopGroup(0, new BasicThreadFactory.Builder().namingPattern("Network Thread %d").daemon(true).build());

    private final Map<PlayerConnection, ChannelFuture> connections = Collections.synchronizedMap(new HashMap<>());

    public void bind(InetAddress address, int port, EuclideanServer server) {
        synchronized(this.connections) {
            PlayerConnection connection = new PlayerConnection(server);
            ChannelFuture future = new ServerBootstrap().channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<>() {
                @Override
                protected void initChannel(Channel channel) {
                    channel.config().setOption(ChannelOption.TCP_NODELAY, true);
                    channel.pipeline()
                            .addLast("timeout", new ReadTimeoutHandler(30))
                            .addLast("decoder", new PacketDecoder())
                            .addLast("prepender", new PacketEncoder.PacketPrepender())
                            .addLast("encoder", new PacketEncoder())
                            .addLast("packet_handler", connection);
                }
            }).group(DEFAULT_CHANNEL).localAddress(address, port).bind().syncUninterruptibly();

            this.connections.put(connection, future);
        }
    }

    public void destroy() {
        connections.forEach((connection, channel) -> {
            try {
                channel.channel().close().sync();
            } catch (Exception ex) {
                System.out.println("Interrupted during channel closing!");
            }
        });
    }
}

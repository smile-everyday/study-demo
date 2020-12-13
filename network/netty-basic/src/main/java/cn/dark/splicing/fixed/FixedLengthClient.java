package cn.dark.splicing.fixed;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.net.InetSocketAddress;

/**
 * @author dark
 * @date 2020-12-12
 */
public class FixedLengthClient {

    public final static String REQUEST = "A, B, C, D";

    public static void main(String[] args) throws InterruptedException {
        FixedLengthClient client = new FixedLengthClient();
        client.start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(9999))
                    .handler(new ChannelInitializerImpl());
            ChannelFuture future = bootstrap.connect().sync();
            System.out.println("客户端已经连接到服务器");
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private static class ChannelInitializerImpl extends ChannelInitializer {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(new FixedLengthFrameDecoder(FixedLengthServer.RESPONSE.length()));
            channel.pipeline().addLast(new FixedLengthClientHandler());
        }
    }
}

package cn.dark.splicing.linebase;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;

/**
 * 基于回车符解决粘包半包
 *
 * @author dark
 * @date 2020-12-12
 */
public class LineBaseServer {

    public static void main(String[] args) throws InterruptedException {
        LineBaseServer server = new LineBaseServer();
        System.out.println("服务器即将启动...");
        server.start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(9999))
                    .childHandler(new ChannelInitializerImpl());
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务器已启动，等待连接...");
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private static class ChannelInitializerImpl extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            channel.pipeline().addLast(new LineBaseServerHandler());
        }
    }

}

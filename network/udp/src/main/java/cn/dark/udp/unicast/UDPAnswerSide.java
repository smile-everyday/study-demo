package cn.dark.udp.unicast;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class UDPAnswerSide {

    public static final int PORT = 8080;
    public static final String ANSWER = "古诗来了：";

    public static void main(String[] args) throws InterruptedException {
        UDPAnswerSide server = new UDPAnswerSide();
        server.start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new AnswerHandler());
            // 没有接收客户端连接的过程，监听本地端口即可
            ChannelFuture future = bootstrap.bind(PORT).sync();
            System.out.println("应答服务已启动...");
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}

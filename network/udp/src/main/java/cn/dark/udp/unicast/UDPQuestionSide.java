package cn.dark.udp.unicast;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class UDPQuestionSide {

    public static final String QUESTION = "告诉我一句古诗";

    public static void main(String[] args) throws InterruptedException {
        UDPQuestionSide questionSide = new UDPQuestionSide();
        questionSide.start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new QuestionHandler());
            Channel channel = bootstrap.bind(0).sync().channel();
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(QUESTION.getBytes()), new InetSocketAddress("127.0.0.1", UDPAnswerSide.PORT))).sync();
            if (!channel.closeFuture().await(15000)) {
                System.out.println("等待应答超时！");
            }
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}

package cn.dark.udp.broadcast.bcside;

import cn.dark.udp.broadcast.LogConst;
import cn.dark.udp.broadcast.LogMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class LogBroadcaster {

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public LogBroadcaster(InetSocketAddress address) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true) // 广播模式
                .handler(new LogMsgEncoder(address));
    }

    public static void main(String[] args) throws InterruptedException {
        LogBroadcaster broadcaster = new LogBroadcaster(new InetSocketAddress("255.255.255.255", LogConst.MONITOR_SIDE_PORT));
        try {
            broadcaster.start();
        } finally {
            broadcaster.stop();
        }
    }

    private void start() throws InterruptedException {
        Channel channel = bootstrap.bind(0).sync().channel();
        int count = 0;
        while (true) {
            channel.writeAndFlush(new LogMsg(null, ++count, LogConst.getLogInfo()));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                Thread.interrupted();
                break;
            }
        }
    }

    private void stop() {
        group.shutdownGracefully();
    }

}

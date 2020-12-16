package cn.dark.udp.broadcast.acceptside;

import cn.dark.udp.broadcast.LogConst;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class LogMonitor {

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public LogMonitor(InetSocketAddress address) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .localAddress(address)
                .option(ChannelOption.SO_REUSEADDR, true) // 端口重用，这样多个监听端才能监听同一个端口
                .option(ChannelOption.SO_BROADCAST, true) // 广播模式
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new LogMsgDecoder())
                                .addLast(new LogEventHandler());
                    }
                });
    }

    public static void main(String[] args) throws InterruptedException {
        LogMonitor monitor = new LogMonitor(new InetSocketAddress(LogConst.MONITOR_SIDE_PORT));
        try {
            Channel channel = monitor.start();
            System.out.println("LogMonitor start...");
            channel.closeFuture().sync();
        } finally {
            monitor.stop();
        }
    }

    private Channel start() {
        return bootstrap.bind().syncUninterruptibly().channel();
    }

    private void stop() throws InterruptedException {
        group.shutdownGracefully().sync();
    }


}

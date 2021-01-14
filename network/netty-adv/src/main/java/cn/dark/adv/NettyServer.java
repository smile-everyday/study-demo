package cn.dark.adv;

import cn.dark.adv.entity.NettyConstant;
import cn.dark.adv.server.ServerInitHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author dark
 * @date 2020-12-19
 */
public class NettyServer {

    private static final Log LOG = LogFactory.getLog(NettyServer.class);

    public static void main(String[] args) throws InterruptedException {
        NettyServer server = new NettyServer();
        server.start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024) // 指定服务端连接队列大小
                .childHandler(new ServerInitHandler());
        bootstrap.bind(NettyConstant.SERVER_PORT).sync();
        LOG.info("Server start...");
    }

}

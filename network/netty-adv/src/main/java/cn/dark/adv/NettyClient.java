package cn.dark.adv;

import cn.dark.adv.client.ClientInitHandler;
import cn.dark.adv.entity.MessageType;
import cn.dark.adv.entity.MyHeader;
import cn.dark.adv.entity.MyMessage;
import cn.dark.adv.entity.NettyConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dark
 * @date 2020-12-19
 */
public class NettyClient implements Runnable {

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private Channel channel;

    /**
     * 负责重连的线程
     */
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /**
     * 是否用户主动关闭
     */
    private volatile boolean isUserClose;

    /**
     * 是否连接成功
     */
    private volatile boolean connected;

    @Override
    public void run() {
        try {
            connect(NettyConstant.SERVER_IP, NettyConstant.SERVER_PORT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connect(String host, int port) throws InterruptedException {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitHandler());
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            channel = future.sync().channel();
            synchronized (this) {
                connected = true;
                notifyAll();
            }
            // 阻塞直到客户端关闭（当调用close方法或者发生异常时）
            future.channel().closeFuture().sync();
        } finally {
            // 非用户主动关闭需要重连
            if (!isUserClose) {
                System.out.println("reconnect...");
                executorService.execute(() -> {
                    try {
                        // 等待操作系统释放资源
                        TimeUnit.SECONDS.sleep(1);
                        connect(NettyConstant.SERVER_IP, NettyConstant.SERVER_PORT);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
            } else {
                // 用户正常关闭，需要唤醒监听该事件的线程
                channel = null;
                eventLoopGroup.shutdownGracefully().sync();
                synchronized (this) {
                    connected = false;
                    notifyAll();
                }
            }
        }
    }

    public void send(Object body) {
        MyHeader header = new MyHeader();
        header.setType(MessageType.SERVICE_REQ.value());

        MyMessage message = new MyMessage();
        message.setMyHeader(header);
        message.setBody(body);
        channel.writeAndFlush(message);
    }

    public boolean isConnected() {
        return connected;
    }

    public void close() {
        isUserClose = true;
        channel.close();
    }
}

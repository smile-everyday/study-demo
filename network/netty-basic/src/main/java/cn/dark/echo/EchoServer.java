package cn.dark.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author dark
 * @date 2020-12-06
 */
public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        EchoServer echoServer = new EchoServer(9999);
        System.out.println("服务器启动...");
        echoServer.start();
        System.out.println("服务器关闭");
    }

    private void start() throws InterruptedException {
        EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class) // 指定通信模式
                    .localAddress(new InetSocketAddress(port))
                    // 服务器事件可以交由工作者线程处理，即childHandler，只有客户端连接成功后才会执行
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 需要在handler上标记@ChannelHandler.Sharable（非线程安全）或者这里每次都new才能在多个channel中使用，
                            // 否则该handler同一时间只能有一个channel使用
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            // 异步绑定服务器端口，sync会阻塞到绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            // 阻塞当前线程，直到服务器ServerChannel被关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}

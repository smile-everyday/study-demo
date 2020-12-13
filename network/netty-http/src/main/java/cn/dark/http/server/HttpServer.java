package cn.dark.http.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;

/**
 * @author dark
 * @date 2020-12-13
 */
public class HttpServer {

    /**
     * 是否开启SSL模式
     */
    private static final boolean SSL = false;

    public static void main(String[] args) throws InterruptedException, CertificateException, SSLException {
        HttpServer server = new HttpServer();
        server.start();
    }

    private void start() throws InterruptedException, CertificateException, SSLException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            SslContext context = null;
            if (SSL) {
                SelfSignedCertificate certificate = new SelfSignedCertificate();
                context = SslContextBuilder.forServer(certificate.certificate(), certificate.privateKey()).build();
            }

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(9999))
                    .childHandler(new ServerHandler(context));
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务器已启动，等待连接...");
            // 监听服务器关闭监听
            future.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            group.shutdownGracefully();
        }
    }

    private static class ServerHandler extends ChannelInitializer<NioSocketChannel> {

        private SslContext context;

        public ServerHandler(SslContext context) {
            this.context = context;
        }

        @Override
        protected void initChannel(NioSocketChannel channel) throws Exception {
            if (context != null) {
                channel.pipeline().addLast(context.newHandler(channel.alloc()));
            }

            channel.pipeline()
                    .addLast("decoder", new HttpRequestDecoder()) // 请求报文解码
                    .addLast("encoder", new HttpResponseEncoder()) // 响应报文编码
                    .addLast("aggregator", new HttpObjectAggregator(10*1024*1024)) // 聚合请求报文
                    .addLast("compressor", new HttpContentCompressor()) // 响应报文压缩，非必须
                    .addLast(new BusinessHandler()); // 业务处理
        }
    }

}

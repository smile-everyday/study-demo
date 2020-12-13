package cn.dark.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * @author dark
 * @date 2020-12-13
 */
public class HttpClient {

    public static void main(String[] args) throws InterruptedException {
        HttpClient client = new HttpClient();
        client.connect();
    }

    private void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    // .remoteAddress(new InetSocketAddress(9999))
                    // .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast("codec", new HttpClientCodec()) // 客户端专用编解码器
                                    .addLast("aggregator", new HttpObjectAggregator(10 * 1024 * 1024)) // 聚合为一个完整的报文
                                    .addLast("decompressor", new HttpContentDecompressor()) // 解压缩
                                    .addLast(new HttpClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}

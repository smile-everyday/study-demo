package cn.dark.adv.client;

import cn.dark.adv.kryo.KryoMessageDecoder;
import cn.dark.adv.kryo.KryoMessageEncoder;
import cn.dark.adv.server.LoginAuthRespHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author dark
 * @date 2020-12-19
 */
public class ClientInitHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 打印日志
        // socketChannel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));

        socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 0,
                2, 0, 2));
        socketChannel.pipeline().addLast(new LengthFieldPrepender(2));

        socketChannel.pipeline().addLast(new KryoMessageDecoder());
        socketChannel.pipeline().addLast(new KryoMessageEncoder());

        // 处理心跳超时
        socketChannel.pipeline().addLast(new ReadTimeoutHandler(15));

        socketChannel.pipeline().addLast(new LoginAuthReqHandler());
        socketChannel.pipeline().addLast(new HeartBeatReqHandler());
    }

}

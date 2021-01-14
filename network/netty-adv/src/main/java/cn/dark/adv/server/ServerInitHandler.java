package cn.dark.adv.server;

import cn.dark.adv.kryo.KryoMessageDecoder;
import cn.dark.adv.kryo.KryoMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author dark
 * @date 2020-12-19
 */
public class ServerInitHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 解决粘包半包
        socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 0,
                2, 0, 2));
        socketChannel.pipeline().addLast(new LengthFieldPrepender(2));

        // 序列化
        socketChannel.pipeline().addLast(new KryoMessageDecoder());
        socketChannel.pipeline().addLast(new KryoMessageEncoder());

        // 登录校验
        socketChannel.pipeline().addLast(new LoginAuthRespHandler());

        // 心跳
        socketChannel.pipeline().addLast(new HeartBeatRespHandler());

        // 业务相关
        socketChannel.pipeline().addLast(new ServerBusiHandler());
    }
}

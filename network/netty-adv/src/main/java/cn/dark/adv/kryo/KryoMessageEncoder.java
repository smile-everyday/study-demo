package cn.dark.adv.kryo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author dark
 * @date 2020-12-19
 */
public class KryoMessageEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        KryoSerializer.serialize(o, byteBuf);
        channelHandlerContext.flush();
    }

}

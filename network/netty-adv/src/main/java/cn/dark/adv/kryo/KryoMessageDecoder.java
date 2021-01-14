package cn.dark.adv.kryo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


/**
 * @author dark
 * @date 2020-12-19
 */
public class KryoMessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object obj = KryoSerializer.deserialize(byteBuf);
        list.add(obj);
    }

}

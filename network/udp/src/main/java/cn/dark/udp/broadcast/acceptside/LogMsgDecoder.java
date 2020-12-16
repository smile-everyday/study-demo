package cn.dark.udp.broadcast.acceptside;

import cn.dark.udp.broadcast.LogMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class LogMsgDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket packet, List<Object> list) throws Exception {
        ByteBuf buf = packet.content();
        long time = buf.readLong();
        System.out.println("接收到" + time + "发送的消息");
        long msgId = buf.readLong();
        byte sep = buf.readByte();
        int idx = buf.readerIndex();
        String msg = buf.slice(idx, buf.readableBytes()).toString(CharsetUtil.UTF_8);
        LogMsg logMsg = new LogMsg(packet.sender(), msg, msgId, time);
        list.add(logMsg);
    }

}

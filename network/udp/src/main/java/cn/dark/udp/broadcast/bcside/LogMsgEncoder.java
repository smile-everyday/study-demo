package cn.dark.udp.broadcast.bcside;

import cn.dark.udp.broadcast.LogMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class LogMsgEncoder extends MessageToMessageEncoder<LogMsg> {

    private InetSocketAddress address;

    public LogMsgEncoder(InetSocketAddress address) {
        this.address = address;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, LogMsg logMsg, List<Object> out) throws Exception {
        byte[] bytes = logMsg.getMsg().getBytes(CharsetUtil.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer(8 * 2 + bytes.length + 1);
        buffer.writeLong(logMsg.getTime());
        buffer.writeLong(logMsg.getMsgId());
        buffer.writeByte(LogMsg.SEPARATOR);
        buffer.writeBytes(bytes);
        ctx.writeAndFlush(new DatagramPacket(buffer, address));
    }
}

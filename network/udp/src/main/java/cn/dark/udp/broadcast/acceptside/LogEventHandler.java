package cn.dark.udp.broadcast.acceptside;

import cn.dark.udp.broadcast.LogMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogMsg logMsg) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(logMsg.getTime());
        builder.append(" [");
        builder.append(logMsg.getSource().toString());
        builder.append("] ：[");
        builder.append(logMsg.getMsgId());
        builder.append("] ：");
        builder.append(logMsg.getMsg());
        //打印 LogMsg 的数据
        System.out.println(builder.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

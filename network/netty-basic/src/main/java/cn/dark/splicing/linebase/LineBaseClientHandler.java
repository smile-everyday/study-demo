package cn.dark.splicing.linebase;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dark
 * @date 2020-12-12
 */
public class LineBaseClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("客户端接收到消息：" + byteBuf.toString(CharsetUtil.UTF_8) + "，累计接收次数：" + atomicInteger.incrementAndGet());
        channelHandlerContext.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = null;
        String request = "A, B, C, DelimiterClientHandler" + System.getProperty("line.separator");
        for (int i = 0; i < 10; i++) {
            buf = Unpooled.buffer(request.length());
            buf.writeBytes(request.getBytes());
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

package cn.dark.splicing.delimiter;

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
public class DelimiterClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        System.out.println("客户端接收到消息：" + byteBuf.toString(CharsetUtil.UTF_8) + "，累计接收次数：" + atomicInteger.incrementAndGet());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String request = "A, B, C, DelimiterClientHandler" + DelimiterServer.DELIMITER;
        for (int i = 0; i < 10; i++) {
            ByteBuf buf = Unpooled.copiedBuffer(request.getBytes());
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

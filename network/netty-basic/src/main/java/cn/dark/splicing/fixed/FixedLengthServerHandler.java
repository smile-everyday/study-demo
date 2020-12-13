package cn.dark.splicing.fixed;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dark
 * @date 2020-12-12
 */
public class FixedLengthServerHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端：[" + ctx.channel().remoteAddress() + "]已建立连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String request = buf.toString(CharsetUtil.UTF_8);
        System.out.println("服务器接收到消息；" + request + "，累计接收消息次数：" + atomicInteger.incrementAndGet());
        ctx.writeAndFlush(Unpooled.copiedBuffer(FixedLengthServer.RESPONSE.getBytes()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 覆盖掉父类方法，因为数据已经写到网络中去，不用再释放资源
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

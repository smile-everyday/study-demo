package cn.dark.http.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author dark
 * @date 2020-12-13
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("/test");
        String msg = "Hello!";
        HttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1,
                HttpMethod.GET,
                uri.toASCIIString(),
                Unpooled.copiedBuffer(msg.getBytes())
        );
        request.headers()
                .set(HttpHeaderNames.HOST, "127.0.0.1")
                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                .set(HttpHeaderNames.CONTENT_LENGTH, ((DefaultFullHttpRequest) request).content().readableBytes());
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;
        System.out.println("响应状态：" + response.status());
        System.out.println("响应头：" + response.headers());
        ByteBuf content = response.content();
        System.out.println("响应结果：" + content.toString(CharsetUtil.UTF_8));
        response.release();
    }
}

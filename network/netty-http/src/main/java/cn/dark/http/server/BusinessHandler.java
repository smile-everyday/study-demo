package cn.dark.http.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author dark
 * @date 2020-12-13
 */
public class BusinessHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址：[" + ctx.channel().remoteAddress() + "]");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String result = "";
        FullHttpRequest request = (FullHttpRequest) msg;
        System.out.println(request.headers());
        try {
            // 请求路径
            String uri = request.uri();
            // 请求body
            String body = request.content().toString(CharsetUtil.UTF_8);
            // 请求方式
            HttpMethod method = request.method();
            System.out.println("接收到：" + method + " 请求");
            if (!"/test".equalsIgnoreCase(uri)) {
                result = "非法请求：" + uri;
                send(ctx, result, HttpResponseStatus.BAD_REQUEST);
                return;
            }

            if (HttpMethod.GET.equals(method)) {
                System.out.println("body：" + body);
                result = "GET请求，应答：" + RespConstant.getNews();
                send(ctx, result, HttpResponseStatus.OK);
                return;
            } else {
                // 其它方式暂不处理
                return;
            }
        } catch (Exception e) {
            System.out.println("处理请求失败");
            e.printStackTrace();
        } finally {
            request.release();
        }
    }

    private void send(ChannelHandlerContext ctx, String result, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                status,
                Unpooled.copiedBuffer(result.getBytes()));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

}

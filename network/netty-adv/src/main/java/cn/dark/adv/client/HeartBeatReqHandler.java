package cn.dark.adv.client;

import cn.dark.adv.entity.MessageType;
import cn.dark.adv.entity.MyHeader;
import cn.dark.adv.entity.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author dark
 * @date 2020-12-19
 */
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

    private static final Log LOG = LogFactory.getLog(HeartBeatReqHandler.class);
    private volatile ScheduledFuture<?> future;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        if (message.getMyHeader() != null
                && MessageType.LOGIN_RESP.value() == message.getMyHeader().getType()) {
            // 每隔10s发出心跳
            future = ctx.executor().scheduleAtFixedRate(() -> {
                ctx.writeAndFlush(buildHeartBeat());
            }, 0, 10, TimeUnit.SECONDS);
            ReferenceCountUtil.release(msg);
        } else if (message.getMyHeader() != null
                && MessageType.HEARTBEAT_RESP.value() == message.getMyHeader().getType()) {
            LOG.info("收到心跳应答：" + message);
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private Object buildHeartBeat() {
        MyHeader myHeader = new MyHeader();
        myHeader.setType(MessageType.HEARTBEAT_REQ.value());

        MyMessage message = new MyMessage();
        message.setMyHeader(myHeader);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

        if (future != null) {
            future.cancel(true);
            future = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}

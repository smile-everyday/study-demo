package cn.dark.adv.server;

import cn.dark.adv.entity.MessageType;
import cn.dark.adv.entity.MyHeader;
import cn.dark.adv.entity.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author dark
 * @date 2020-12-19
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {

    private static final Log LOG = LogFactory.getLog(HeartBeatRespHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        if (message.getMyHeader() != null
                && MessageType.HEARTBEAT_REQ.value() == message.getMyHeader().getType()) {
            MyMessage hearBeatResp = buildResponse();
            ctx.writeAndFlush(hearBeatResp);
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private MyMessage buildResponse() {
        MyHeader myHeader = new MyHeader();
        myHeader.setType(MessageType.HEARTBEAT_RESP.value());

        MyMessage message = new MyMessage();
        message.setMyHeader(myHeader);
        return message;
    }

}

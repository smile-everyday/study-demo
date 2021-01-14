package cn.dark.adv.client;

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
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    private static final Log LOG = LogFactory.getLog(LoginAuthReqHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildRequest());
    }

    private Object buildRequest() {
        MyHeader myHeader = new MyHeader();
        myHeader.setType(MessageType.LOGIN_REQ.value());

        MyMessage message = new MyMessage();
        message.setMyHeader(myHeader);
        return message;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        if (message.getMyHeader() != null
                && MessageType.LOGIN_RESP.value() == message.getMyHeader().getType()) {
            if ((byte) message.getBody() != (byte) 0) {
                ctx.close();
                ReferenceCountUtil.release(msg);
            } else {
                LOG.info("Login success: " + message);
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

}

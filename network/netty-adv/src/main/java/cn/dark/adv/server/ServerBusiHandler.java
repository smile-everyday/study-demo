package cn.dark.adv.server;

import cn.dark.adv.entity.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author dark
 * @date 2020-12-19
 */
public class ServerBusiHandler extends SimpleChannelInboundHandler<MyMessage> {

    private static final Log LOG = LogFactory.getLog(ServerBusiHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyMessage myMessage) throws Exception {
        LOG.info("Accept request: " + myMessage );
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOG.info("remote [" + ctx.channel().remoteAddress().toString() + "] close the connection");
    }
}

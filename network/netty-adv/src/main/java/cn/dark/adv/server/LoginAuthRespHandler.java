package cn.dark.adv.server;

import cn.dark.adv.entity.MessageType;
import cn.dark.adv.entity.MyHeader;
import cn.dark.adv.entity.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dark
 * @date 2020-12-19
 */
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {

    private static final Log LOG = LogFactory.getLog(LoginAuthRespHandler.class);

    private static final Set<String> WHITE_LIST = new HashSet<>();
    private static Map<String, Boolean> loginCheck = new ConcurrentHashMap<>();

    static {
        WHITE_LIST.add("127.0.0.1");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage myMessage = (MyMessage) msg;
        // 握手消息
        if (myMessage.getMyHeader() != null
                && MessageType.LOGIN_REQ.value() == myMessage.getMyHeader().getType()) {
            SocketAddress address = ctx.channel().remoteAddress();
            MyMessage loginResp = null;
            if (loginCheck.putIfAbsent(address.toString(), true) == null) {
                // 登录成功
                InetSocketAddress socketAddress = (InetSocketAddress) address;
                if (!WHITE_LIST.contains(socketAddress.getHostString())) {
                    loginResp = buildResponse((byte) -1);
                } else {
                    loginResp = buildResponse((byte) 0);
                }
            } else {
                // 重复登录，拒绝登录
                loginResp = buildResponse((byte) -1);
            }

            LOG.info("Login response is [" + loginResp + "]");
            ctx.writeAndFlush(loginResp);
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        loginCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    private MyMessage buildResponse(byte b) {
        MyHeader myHeader = new MyHeader();
        myHeader.setType(MessageType.LOGIN_RESP.value());

        MyMessage message = new MyMessage();
        message.setMyHeader(myHeader);
        message.setBody(b);
        return message;
    }
}

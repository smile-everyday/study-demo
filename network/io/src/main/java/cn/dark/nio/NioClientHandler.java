package cn.dark.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dark
 * @date 2020-12-05
 */
public class NioClientHandler implements Runnable {

    private String ip;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;

    public NioClientHandler(String ip, int port) {
        try {
            this.ip = ip;
            this.port = port;

            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            doConnect();

            while (true) {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    handleKey(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }

        SocketChannel sc = (SocketChannel) key.channel();
        if (key.isConnectable()) {
            // 连接成功注册对读事件的监听，否则退出系统
            if (sc.finishConnect()) {
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                System.exit(1);
            }
        }

        if (key.isReadable()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = sc.read(buffer);
            if (read > 0) {
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                String message = new String(bytes);
                System.out.println("客户端收到的消息：" + message);
            } else if (read < 0) {
                key.cancel();
                sc.close();
            }
        }
    }

    private void doConnect() throws IOException {
        // 连接成功，注册对读事件的监听，否则监听连接事件
        if (socketChannel.connect(new InetSocketAddress(ip, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    public void sendMessage(String msg) {
        try {
            doWrite(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String msg) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(msg.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
    }
}

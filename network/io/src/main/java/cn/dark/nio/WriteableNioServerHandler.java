package cn.dark.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dark
 * @date 2020-12-05
 */
public class WriteableNioServerHandler implements Runnable {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public WriteableNioServerHandler(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            // 区别？
            // serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 超时阻塞获取当前事件，selectNow()是非阻塞
                selector.select(1000);
                // 获取事件集合，keys方法返回的结果是不可更改的，而该方法的返回的结果可以修改
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 处理过的key必须删除，否则会重复获取该事件
                    iterator.remove();
                    handleKey(selectionKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleKey(SelectionKey selectionKey) throws IOException {
        // 排除掉无效事件
        if (!selectionKey.isValid()) {
            return;
        }

        if (selectionKey.isAcceptable()) {
            // 接收客户端连接
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = channel.accept();
            System.out.println("建立连接...");
            socketChannel.configureBlocking(false);

            // 监听读事件
            socketChannel.register(selector, SelectionKey.OP_READ);
        }

        if (selectionKey.isReadable()) {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 从通道中读取数据到buffer
            int readBytes = channel.read(buffer);
            if (readBytes > 0) {
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                // 读取buffer数据并写入到byte数组中
                buffer.get(bytes);
                String message = new String(bytes);
                System.out.println("服务器接收到消息：" + message);

                String response = Const.response(message);
                doWrite(channel, response);
            } else if (readBytes < 0){
                // 取消对该事件的监听
                selectionKey.cancel();
                channel.close();
            }
        }

        if (selectionKey.isWritable()) {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
            if (buffer.hasRemaining()) {
                int count = channel.write(buffer);
                System.out.println("write " + count + "bytes, remaining " + buffer.remaining() + "bytes");
            } else {
                // 取消对写事件的监听，因为只要buffer有空间就会触发写事件，若不取消，
                // 就会持续接收到写事件，所以写事件适用于待写资源大小远远大于buffer大小的情况
                // 这里还不能使用cancel，会同时取消对读事件的监听
                selectionKey.interestOps(SelectionKey.OP_READ);
            }
        }
    }

    private void doWrite(SocketChannel channel, String message) throws IOException {
        byte[] bytes = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        // 读取buffer中的数据并写入到channel
        buffer.flip();
        // 注册对读、写事件的监听，并同时把buffer传递过去
        channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, buffer);
    }
}

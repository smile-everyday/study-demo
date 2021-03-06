package cn.dark.nio;

import static cn.dark.nio.Const.DEFAULT_PORT;

/**
 * @author dark
 * @date 2020-12-05
 */
public class WriteableNioServer {

    public static void main(String[] args) {
        WriteableNioServerHandler serverHandler = new WriteableNioServerHandler(DEFAULT_PORT);
        new Thread(serverHandler).start();
    }

}

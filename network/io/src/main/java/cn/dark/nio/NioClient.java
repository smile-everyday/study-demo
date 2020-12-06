package cn.dark.nio;

import java.util.Scanner;

/**
 * @author dark
 * @date 2020-12-05
 */
public class NioClient {

    private static NioClientHandler nioClientHandler;

    public static void main(String[] args) {
        start();

        Scanner scanner = new Scanner(System.in);
        while (sendMessage(scanner.next()));
    }

    private static boolean sendMessage(String next) {
        nioClientHandler.sendMessage(next);
        return true;
    }

    private static void start() {
        nioClientHandler = new NioClientHandler(Const.DEFAULT_SERVER_IP, Const.DEFAULT_PORT);
        new Thread(nioClientHandler).start();
    }

}

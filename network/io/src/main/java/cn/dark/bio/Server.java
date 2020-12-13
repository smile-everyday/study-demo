package cn.dark.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dark
 * @date 2020-12-03
 */
public class Server {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress address = new InetSocketAddress(6000);
        serverSocket.bind(address);
        System.out.println("Server start...");
        while (true) {
            executorService.execute(new ServerTask(serverSocket.accept()));
        }
    }

    private static class ServerTask implements Runnable {

        private Socket socket;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
                String name = ois.readUTF();
                System.out.println("接到客人：" + name);

                oos.writeUTF("你好, " + name);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

package cn.dark.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author dark
 * @date 2020-12-03
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6000);
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            oos.writeUTF("Lwj");
            oos.flush();

            String result = ois.readUTF();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

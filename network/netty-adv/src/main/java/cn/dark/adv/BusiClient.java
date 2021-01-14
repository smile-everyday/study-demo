package cn.dark.adv;

import cn.dark.adv.busiEntity.User;
import cn.dark.adv.busiEntity.UserContact;

import java.util.Scanner;

/**
 * @author dark
 * @date 2020-12-19
 */
public class BusiClient {

    public static void main(String[] args) throws InterruptedException {
        NettyClient client = new NettyClient();
        new Thread(client).start();

        while (!client.isConnected()) {
            synchronized (client) {
                client.wait();
            }
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.next();
            if (str == null || str.equalsIgnoreCase("")) {
                continue;
            }

            if ("q".equalsIgnoreCase(str)) {
                client.close();
                // 等待客户端channel正常关闭
                while (client.isConnected()) {
                    synchronized (client) {
                        client.wait();
                    }
                }
                scanner.close();
                System.exit(1);
            } else if ("v".equalsIgnoreCase(str)) {
                UserContact userContact = new UserContact();
                userContact.setPhone("18280336666");
                userContact.setMail("1213545@qq.com");

                User user = new User();
                user.setId("1");
                user.setAge(18);
                user.setUserName("dark");
                user.setUserContact(userContact);
                client.send(user);
            } else {
                client.send(str);
            }
        }
    }

}

package cn.dark.dynamicproxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Title:
 * @Author: Dark
 * @Date: 2018-08-25
 */
public class MainClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        MyInvocationHandler handler = new MyInvocationHandler();
        // Get proxy object
        Game instance = handler.getInstance(new BlizzardGame());
        instance.publicize("Nine City");
        instance.operate("Nine City");

        System.out.println(Class.forName("cn.dark.dynamicproxy.jdk.BlizzardGame"));

        // Recreated source code file form ram
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{instance.getClass()});
        FileOutputStream fo = new FileOutputStream("$Proxy0.class");
        fo.write(bytes);
        fo.close();
    }

}

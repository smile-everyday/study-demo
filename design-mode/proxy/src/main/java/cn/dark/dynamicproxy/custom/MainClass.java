package cn.dark.dynamicproxy.custom;

/**
 * @Title:
 * @Author: Dark
 * @Date: 2018-08-25
 */
public class MainClass {

    public static void main(String[] args) throws Throwable {

        MyInvocationHandler handler = new MyInvocationHandler();
        // Get proxy object
        Game instance = handler.getInstance(new BlizzardGame());
        instance.publicize("Nine City");
        instance.operate("Nine City");

    }

}

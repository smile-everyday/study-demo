package cn.dark.dynamicproxy.cglib;

/**
 * @Title:
 * @Author: Dark
 * @Date: 2018-08-26
 */
public class MainClass {

    public static void main(String[] args) {
        MyInterceptor interceptor = new MyInterceptor();
        BlizzardGame instance = (BlizzardGame) interceptor.getInstance(new BlizzardGame());
        instance.publicize("Nine City");
        instance.operate("Nine City");
    }

}

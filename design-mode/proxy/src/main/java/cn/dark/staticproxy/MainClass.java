package cn.dark.staticproxy;

/**
 * @Title:
 * @Author: Dark
 * @Date: 2018-08-25
 */
public class MainClass {

    public static void main(String[] args) {
        Game proxy = new NineCity(new BlizzardGame());
        proxy.operate(proxy.getClass().getSimpleName());
    }

}

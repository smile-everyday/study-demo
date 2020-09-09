package cn.dark.three;

/**
 * 饿汉
 *
 * @author dark
 * @date 2018-09-22
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }

}

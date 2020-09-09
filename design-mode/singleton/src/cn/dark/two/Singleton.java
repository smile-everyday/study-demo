package cn.dark.two;

/**
 * 懒汉-线程安全
 *
 * @author dark
 * @date 2018-09-22
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

}

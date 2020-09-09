package cn.dark.five;

/**
 * 双重校验锁
 *
 * @author dark
 * @date 2018-09-22
 */
public class Singleton {

    // volatile是为了禁止指令重排序，必不可少
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

}

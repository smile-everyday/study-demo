package cn.dark.four;

/**
 * 静态内部类
 *
 * @author dark
 * @date 2018-09-22
 */
public class Singleton {

    private Singleton() {}

    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
        static {
            System.out.println("init");
        }
    }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}

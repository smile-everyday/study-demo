package cn.dark.six;

/**
 * @author dark
 * @date 2019-01-21
 */
public class SingletonEnum {

    private SingletonEnum() {

    }

    public static SingletonEnum getInstance() {
        return Singleton1.INSTANCE.getSingleton();
    }

    private enum Singleton1 {
        INSTANCE;

        private SingletonEnum singleton;

        // jvm保证这个方法绝对只执行一次
        Singleton1() {
            singleton = new SingletonEnum();
        }
        public SingletonEnum getSingleton() {
            return singleton;
        }

    }
}

package cn.dark.mydemo;

/**
 * @author dark
 * @date 2020-08-29
 */
public class TestLock {

    private static int sum;
    private static MyLock lock = new MyLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> sum());
        Thread t2 = new Thread(() -> sum());
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(sum);
    }

    private static void sum () {
        lock.lock();
        for (int i = 0; i < 10000; i++) {
            sum++;
        }
        lock.unlock();
    }

}

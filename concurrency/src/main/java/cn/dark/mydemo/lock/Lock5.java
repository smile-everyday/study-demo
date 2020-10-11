package cn.dark.mydemo.lock;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁demo
 *
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class Lock5 {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock r = lock.readLock();
    private static Lock w = lock.writeLock();

    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    /**
     * 验证当前线程持有读锁并让其它线程获取写锁阻塞，再让当前线程获取读锁，看是否能够重入；
     * 验证是可以重入的，否则会造成死锁
     *
     *
     */
    private static void test() throws InterruptedException {
        new Thread(() -> {
            r.lock();
            try {
                log.info("t1线程获取到读锁");
                TimeUnit.SECONDS.sleep(5);
                r.lock();
                try {
                    log.info("t1线程重入成功");
                } finally {
                    r.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
            }
        }, "t1").start();

        // 确保t1进入睡眠
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            w.lock();
            try {
                log.info("t2获取写锁");
            } finally {
                w.unlock();
            }
        }, "t2").start();
    }

    /**
     * 读读共享、读写互斥
     *
     */
    private static void test1() throws InterruptedException {
        new Thread(() -> {
            r.lock();
            try {
                log.info("t1线程获取到读锁");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
                log.info("t1释放锁");
            }
        }, "t1").start();

        // 确保t1进入睡眠
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            r.lock();
            try {
                log.info("t2线程获取到读锁");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
                log.info("t2释放锁");
            }
        }, "t2").start();

        new Thread(() -> {
            w.lock();
            try {
                log.info("t3获取写锁");
            } finally {
                w.unlock();
                log.info("t3释放锁");
            }
        }, "t3").start();

    }

    /**
     * 两个读锁之间加了一个写锁，这种情况下两个读写无法共享
     *
     */
    private static void test2() throws InterruptedException {
        new Thread(() -> {
            r.lock();
            try {
                log.info("t1线程获取到读锁");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
                log.info("t1释放锁");
            }
        }, "t1").start();

        // 确保t1进入睡眠
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            w.lock();
            try {
                log.info("t2线程获取到写锁");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                w.unlock();
                log.info("t2释放锁");
            }
        }, "t2").start();

        // 确保t2进入睡眠
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            r.lock();
            try {
                log.info("t3获取读锁");
            } finally {
                r.unlock();
                log.info("t3释放锁");
            }
        }, "t3").start();

    }


}

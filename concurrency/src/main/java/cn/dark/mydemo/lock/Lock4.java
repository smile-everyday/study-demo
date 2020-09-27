package cn.dark.mydemo.lock;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试lock的打断
 *
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class Lock4 {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                log.info("t2获取到锁");
                log.info("t2发生竞争获取到锁后的打断状态：{}", Thread.currentThread().isInterrupted());
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        TimeUnit.SECONDS.sleep(1);

        t2.interrupt();
    }


}

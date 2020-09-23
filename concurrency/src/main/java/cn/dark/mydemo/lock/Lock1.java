package cn.dark.mydemo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition测试
 *
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class Lock1 {

    private Lock lock = new ReentrantLock();
    private Condition wait1 = lock.newCondition();
    private Condition wait2 = lock.newCondition();

    private boolean isReady1 = false;
    private boolean isReady2 = false;

    public static void main(String[] args) throws InterruptedException {
        new Lock1().test();
    }

    private void test() throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                while (!isReady1) {
                    try {
                        log.info("通道一满员，开始排队");
                        wait1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("通道一排队完成");
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                while (!isReady2) {
                    try {
                        log.info("通道二满员，开始排队");
                        wait2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("通道二排队完成");
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        log.info("休息三秒");
        Thread.sleep(3000);
        log.info("休息结束");

        lock.lock();
        try {
            isReady1 = true;
            wait1.signal();
            isReady2 = true;
            wait2.signal();
        } finally {
            lock.unlock();
        }
    }

}

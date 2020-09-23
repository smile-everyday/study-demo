package cn.dark.mydemo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁重入
 *
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class Lock3 {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock r = lock.readLock();
    private static Lock w = lock.writeLock();

    public static void main(String[] args) {
        // 读写锁只有降级时才支持重入，即写锁到读锁
        // rw();
        wr();
    }

    private static void rw() {
        r.lock();
        try {
            log.info("获取到读锁");
            w.lock();
            try {
                log.info("获取到写锁");
            } finally {
                w.unlock();
            }
        } finally {
            r.unlock();
        }
    }

    private static void wr() {
        w.lock();
        try {
            log.info("获取到写锁");
            r.lock();
            try {
                log.info("获取到读锁");
            } finally {
                r.unlock();
            }
        } finally {
            w.unlock();
        }
    }

}

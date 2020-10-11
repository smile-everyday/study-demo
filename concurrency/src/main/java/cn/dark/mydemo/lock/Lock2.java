package cn.dark.mydemo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class Lock2 {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock r = lock.readLock();
    private static Lock w = lock.writeLock();

    public static void main(String[] args) {
        // rr();
        rw();
        // ww();
    }

    private static void rr() {
        new Thread(() -> {
            r.lock();
            try {
                print();
            } finally {
                r.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            r.lock();
            try {
                print();
            } finally {
                r.unlock();
            }
        }, "t2").start();
    }

    private static void rw() {
        new Thread(() -> {
            r.lock();
            try {
                print();
            } finally {
                r.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            w.lock();
            try {
                print();
            } finally {
                w.unlock();
            }
        }, "t2").start();
    }

    private static void ww() {
        new Thread(() -> {
            w.lock();
            try {
                print();
            } finally {
                w.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            w.lock();
            try {
                print();
            } finally {
                w.unlock();
            }
        }, "t2").start();
    }

    private static void print() {
        for (int i = 0; i < 5; i++) {
            log.info("exec：" + i);
        }
    }

}

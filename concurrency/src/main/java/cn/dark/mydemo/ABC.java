package cn.dark.mydemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建3个线程：轮流打印abc，共打印4次
 *
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class ABC {

    private int sort = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ABC lock4 = new ABC();
        // new Thread(() -> {
        //     lock4.test("a", 0, 1);
        // }, "t1").start();
        // new Thread(() -> {
        //     lock4.test("b", 1, 2);
        // }, "t2").start();
        // new Thread(() -> {
        //     lock4.test("c", 2, 0);
        // }, "t3").start();

        new Thread(() -> {
            lock4.test1("a", 0, lock4.condition1, lock4.condition2);
        }, "t1").start();
        new Thread(() -> {
            lock4.test1("b", 1, lock4.condition2, lock4.condition3);
        }, "t2").start();
        new Thread(() -> {
            lock4.test1("c", 2, lock4.condition3, lock4.condition1);
        }, "t3").start();
    }

    private void test(String msg, int sort, int next) {
        for (int i = 0; i < 4; i++) {
            synchronized (this) {
                while (this.sort != sort) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(msg);
                this.sort = next;
                notifyAll();

                if (sort == 2) {
                    System.out.println();
                }
            }
        }

    }

    private void test1(String msg, int target, Condition cur, Condition next) {
        for (int i = 0; i < 4; i++) {
            lock.lock();
            try {
                while (sort % 3 != target) {
                    cur.await();
                }

                System.out.print(msg);
                sort++;
                next.signal();

                if (sort % 3 == 0) {
                    System.out.println();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

}

package cn.dark.mydemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建3个线程：轮流打印abc，共打印4次
 *
 * @author dark
 * @date 2020-09-23
 */
public class ABC {

    private int sort = 0;

    private volatile int symbo = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private Semaphore semaphore1 = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private Semaphore semaphore3 = new Semaphore(0);

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

        // new Thread(() -> {
        //     lock4.test1("a", 0, lock4.condition1, lock4.condition2);
        // }, "t1").start();
        // new Thread(() -> {
        //     lock4.test1("b", 1, lock4.condition2, lock4.condition3);
        // }, "t2").start();
        // new Thread(() -> {
        //     lock4.test1("c", 2, lock4.condition3, lock4.condition1);
        // }, "t3").start();

        // new Thread(() -> lock4.test2("a", lock4.semaphore1, lock4.semaphore2)).start();
        // new Thread(() -> lock4.test2("b", lock4.semaphore2, lock4.semaphore3)).start();
        // new Thread(() -> lock4.test2("c", lock4.semaphore3, lock4.semaphore1)).start();

        new Thread(() -> lock4.test3("a", 0)).start();
        new Thread(() -> lock4.test3("b", 1)).start();
        new Thread(() -> lock4.test3("c", 2)).start();
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

    private void test2(String msg, Semaphore cur, Semaphore next) {
        for (int i = 0; i < 5; i++) {
            try {
                cur.acquire();
                System.out.print(msg);
                if (semaphore3 == cur) {
                    System.out.println();
                }
                next.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void test3(String msg, int target) {
        while (true) {
            if (this.symbo % 3 == target) {
                System.out.print(msg);
                this.symbo++;
            }
        }
    }

    private void test4(String msg, int target, Thread next) {
        for (int i = 0; i < 4; i++) {
            if (this.sort % 3 == target) {

            }
        }
    }

}

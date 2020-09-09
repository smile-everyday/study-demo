package cn.dark.mydemo;

import cn.dark.entity.A;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * 关闭偏向延迟：-XX:BiasedLockingStartupDelay=0
 *
 * @author dark
 * @date 2020-09-03
 */
@Slf4j
public class SyncExpansion {

    private static A a = new A();
    private static Thread t1;
    private static Thread t2;

    public static void main(String[] args) throws InterruptedException {
        demo2();
    }

    private static void demo1() throws InterruptedException {
        log.debug("获取锁之前：{}", ClassLayout.parseInstance(a).toPrintable());
        Thread t1 = new Thread(() -> {
            lockA();
        });
        t1.setName("t1");
        t1.start();
        Thread.sleep(3000);
        log.debug("t1获取锁之后：{}", ClassLayout.parseInstance(a).toPrintable());

        Thread t2 = new Thread(() -> {
            lockA();
        });
        t2.setName("t2");
        t2.start();
        Thread.sleep(3000);
        log.debug("t2获取锁之后：{}", ClassLayout.parseInstance(a).toPrintable());
    }

    private static void demo2() throws InterruptedException {
        log.debug("获取锁之前：{}", ClassLayout.parseInstance(a).toPrintable());
        t1 = new Thread() {
            @Override
            public void run() {
                lockA();
                t2.setName("t2");
                t2.start();
            }
        };
        // Thread.sleep(3000);
        log.debug("t1获取锁之后：{}", ClassLayout.parseInstance(a).toPrintable());

        t2 = new Thread() {
            @Override
            public void run() {
                lockA();
            }
        };
        t1.setName("t1");
        t1.start();
        Thread.sleep(3000);
        log.debug("t2获取锁之后：{}", ClassLayout.parseInstance(a).toPrintable());
    }

    private static void lockA() {
        synchronized (a) {
            log.debug("当前线程名称：{}", Thread.currentThread().getName());
            log.debug("获取锁：{}", ClassLayout.parseInstance(a).toPrintable());
        }
    }

}

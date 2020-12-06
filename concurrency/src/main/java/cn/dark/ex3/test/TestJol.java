package cn.dark.ex3.test;

import cn.dark.entity.A;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class TestJol {

    static A l = new A();

    static Thread t1;
    static Thread t2;
    public static void main(String[] args) throws InterruptedException {
        t1 = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                testLock();
                Thread.sleep(1000);
                testLock();
            }
        };

        t2 = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(3000);
                testLock();
            }
        };

        t1.setName("t1");
        t1.start();
        t2.setName("t2");
        t2.start();

    }

    public static void testLock() {
        synchronized (l) {
            log.debug("name:" + Thread.currentThread().getName());
            log.debug(ClassLayout.parseInstance(l).toPrintable());
        }

    }
}

package cn.dark.mydemo.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一道面试题：实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 * 当个数到5个时，线程2给出提示并结束
 *
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class Container2 {

    private List list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public static void main(String[] args) {
        new Container2().test();
    }

    private void test() {
        new Thread(() -> {
            synchronized (this) {
                if (list.size() < 5) {
                    try {
                        log.info("数量不足5，开始等待");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("装满5个了");
                notify();
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    log.info("已经装了{}个", list.size());
                    if (list.size() != 5) {
                        // notify后释放锁，让t1执行，其实直接break更好一些
                        notify();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "t1").start();

    }

}

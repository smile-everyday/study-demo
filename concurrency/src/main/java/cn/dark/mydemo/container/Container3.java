package cn.dark.mydemo.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class Container3 {

    private List list = new ArrayList<>();

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void add(Object o) {
        list.add(o);
    }

    public static void main(String[] args) throws InterruptedException {
        new Container3().test();
    }

    private void test() throws InterruptedException {
        new Thread(() -> {
            if (list.size() != 5) {
                log.info("数量不足5，开始等待");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("装满5个了");
        }, "t2").start();

        new Thread(() -> {
            while (true) {
                list.add(1);
                log.info("已经装了{}个", list.size());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size() == 5) {
                    countDownLatch.countDown();
                    break;
                }
            }
        }, "t1").start();

    }

}

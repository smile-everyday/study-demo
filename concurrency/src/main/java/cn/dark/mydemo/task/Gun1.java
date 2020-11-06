package cn.dark.mydemo.task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 采用多线程技术，例如wait/notify，设计实现一个符合生产者和消费者问题的程序，
 * 对某一个对象（枪膛）进行操作，其最大容量是20颗子弹，生产者线程是一个压入线程，
 * 它不断向枪膛中压入子弹，消费者线程是一个射出线程，它不断从枪膛中射出子弹。
 *
 * @author dark
 * @date 2020-11-05
 */
@Slf4j
public class Gun1 {

    private static int zd = 0;

    private void put() throws InterruptedException {
        synchronized (this) {
            while (zd >= 20) {
                log.info("子弹已经装满");
                notifyAll();
                wait();
            }

            zd++;
            log.info("装填第{}颗子弹", zd);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    private void get() throws InterruptedException {
        synchronized (this) {
            while (zd <= 0) {
                log.info("子弹已经射完");
                notifyAll();
                wait();
            }

            log.info("射出第{}颗子弹", zd);
            zd--;
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    static class Product implements Runnable {
        private Gun1 gun;

        public Product(Gun1 gun) {
            this.gun = gun;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    gun.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private Gun1 gun;

        public Consumer(Gun1 gun) {
            this.gun = gun;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    gun.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Gun1 gun1 = new Gun1();
        new Thread(new Product(gun1), "生产者").start();
        new Thread(new Consumer(gun1), "消费者").start();
    }


}

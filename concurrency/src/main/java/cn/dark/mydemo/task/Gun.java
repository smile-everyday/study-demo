package cn.dark.mydemo.task;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
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
public class Gun {

    private LinkedList<Integer> barrel = new LinkedList<>();

    public static void main(String[] args) {
        Gun gun = new Gun();
        new Thread(() -> {
            try {
                gun.push();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                gun.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void push() throws InterruptedException {
        synchronized (this) {
            while (true) {
                if (barrel.size() < 20) {
                    barrel.addLast(barrel.size() + 1);
                    log.info("已经装填{}颗子弹", barrel.size());
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    notifyAll();
                    wait();
                }
            }
        }
    }

    private void take() throws InterruptedException {
        synchronized (this) {
            while (true) {
                if (barrel.size() > 0) {
                    Integer i = barrel.removeFirst();
                    log.info("射出第{}颗子弹", i);
                } else {
                    notifyAll();
                    wait();
                }
            }
        }
    }

}

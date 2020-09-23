package cn.dark.mydemo.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dark
 * @date 2020-09-12
 */
@Slf4j
public class Demo2 {

    private static Demo2 lock = new Demo2();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                synchronized (lock) {
                    log.info(Thread.currentThread().getName());
                }
            });
        }

        synchronized (lock) {
            for (Thread thread : threads) {
                thread.start();
                // 睡眠一下保证线程的启动顺序
                Thread.sleep(100);
            }
        }
    }

}

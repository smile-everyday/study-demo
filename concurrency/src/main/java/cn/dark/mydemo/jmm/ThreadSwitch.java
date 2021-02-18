package cn.dark.mydemo.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dark
 * @date 2020-10-12
 */
@Slf4j
public class ThreadSwitch {

    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    /**
     * 验证线程切换，这里会出现以下几种结果：
     *     1. x = 0, y = 1：t1先启动，执行完后t2启动
     *     2. x = 1, y = 1：t1先启动，执行完a=1后切换到t2执行b=1,
     *     3. x = 1, y = 0：t2先启动，执行完后t1启动
     *     4. x = 0, y = 0：只有发生指令重排序，将x=b或y=a提到线程第一行且发生线程切换时才会出现，
     *     可以在字段上加上volatile禁止指令重排序
     * 指令重排序可以减少指令的条数，提高执行指令的效率
     *
     * @date 2020-10-12
     *
     */
    public static void main(String[] args) throws InterruptedException {
        int i = 0;

        while (true) {
            x = y = a = b = 0;

            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            log.info("第{}次的结果：x={}，y={}", ++i, x, y);
            if (x == 0 && y == 0) {
                break;
            }
        }
    }

}

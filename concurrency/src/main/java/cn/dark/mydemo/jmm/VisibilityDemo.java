package cn.dark.mydemo.jmm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author dark
 * @date 2020-10-12
 */
@Slf4j
public class VisibilityDemo {

    private static boolean flag = false;
    private static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    /**
     * 这个方法不能证明可见性，因为本质上是编译优化导致的，优化后代码如下：
     *      if(!flag) {
     *          while(true) {
     *              i++;
     *          }
     *      }
     * 当在变量i上加了volatile修饰或在while中使用System.out.println时都会导致
     * 取消编译优化，所以可以让程序停止
     *
     * @date 2020-10-12
     *
     */
    private static void test1() throws InterruptedException {
        new Thread(() -> {
            while (!flag) {
                i++;
            }
            log.info("" + i);
        }).start();

        TimeUnit.SECONDS.sleep(1);

        flag = true;
    }

}

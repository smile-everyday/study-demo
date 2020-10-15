package cn.dark.mydemo.jmm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author dark
 * @date 2020-10-12
 */
@Slf4j
public class HB {

    private static int i = 0;
    private static volatile boolean flag = false;

    /**
     * 验证happens-before的传递性：因为flag加了volatile，对该变量的写hb于对该变量的读，
     * 因此flag的修改后的值对主线程可见，又因为变量i的赋值hb于flag的赋值，所以i不用加volatile也可以，
     * 利用该规则可以实现volatile触发器。
     * 注：hb的传递性在JDK4及以前没有，所以可以对比验证，但由于可见性很难证明，所以即使使用JDK4也可能无法演示出
     * i=0&flag=true的情况
     *
     * @date 2020-10-12
     *
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            i = 50;
            flag = true;
        }).start();

        TimeUnit.MICROSECONDS.sleep(10);

        log.info("i:{},flag:{}", i, flag);
    }

}

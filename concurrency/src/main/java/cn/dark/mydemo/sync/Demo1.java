package cn.dark.mydemo.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lwj
 * @date 2020-09-10
 */
@Slf4j
public class Demo1 {

    private boolean running = true;

    public void exec() {
        log.debug("start");
        while (running) {

        }
        log.error("end");
    }

    public static void main(String[] args) throws InterruptedException {
        Demo1 demo1 = new Demo1();
        new Thread(demo1::exec, "t1").start();

        Thread.sleep(1000);

        demo1.running = false;
    }

}

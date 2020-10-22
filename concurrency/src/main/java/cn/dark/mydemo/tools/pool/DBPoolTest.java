package cn.dark.mydemo.tools.pool;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lwj
 * @date 2020-10-22
 */
@Slf4j
public class DBPoolTest {

    private static DBPool pool = new DBPool(10);
    private static SemaphoreDBPool semaphoreDBPool = new SemaphoreDBPool();
    private static AtomicInteger got = new AtomicInteger(0);
    private static AtomicInteger unGot = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // waitPoolTest();

        semaphorePoolTest();

        TimeUnit.SECONDS.sleep(5);
        log.info("获取连接数量：{}", got.get());
        log.info("未获取连接数量：{}", unGot.get());
    }

    private static void semaphorePoolTest() {
        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                Connection connection = connection = semaphoreDBPool.getConnection();
                if (connection != null) {
                    log.info("获取连接：{}", got.incrementAndGet());

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphoreDBPool.release(connection);
                    }
                } else {
                    log.info("获取连接失败：{}", unGot.incrementAndGet());
                }
            }, "t" + i).start();
        }
    }

    private static void waitPoolTest() {
        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                Connection connection = null;
                try {
                    connection = pool.getConnection(1900);
                    log.info("获取连接：{}", got.incrementAndGet());
                } catch (TimeoutException e) {
                    log.info(e.getMessage());
                    unGot.incrementAndGet();
                }

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    pool.release(connection);
                }
            }, "t" + i).start();
        }
    }

}

package cn.dark.mydemo.tools.pool;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author lwj
 * @date 2020-10-22
 */
@Slf4j
public class SemaphoreDBPool {

    private static int initSize = 10;
    private static Semaphore unuse, used;
    private static LinkedList<Connection> pool = new LinkedList<>();

    public SemaphoreDBPool() {
        for (int i = 0; i < initSize; i++) {
            pool.add(new MyConnection());
        }

        unuse = new Semaphore(10);
        used = new Semaphore(0);
    }

    public Connection getConnection() {
        try {
            unuse.acquire();
            Connection connection = null;
            synchronized (pool) {
                connection = pool.removeFirst();
            }
            used.release();
            return connection;
        } catch (InterruptedException e) {
            log.info("忽略打断");
        }
        return null;
    }

    public void release(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            /**
             * Semaphore的release方法可以无限调用，且每次调用都会增加一个信号量，
             * 所以需要另外一个Semaphore来控制原始Semaphore不超出
             */
            used.acquire();
            synchronized (pool) {
                pool.addLast(connection);
            }
            unuse.release();
        } catch (InterruptedException e) {
            log.info("忽略打断");
        }
    }

}

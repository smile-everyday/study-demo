package cn.dark.mydemo.tools.pool;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.TimeoutException;

/**
 * @author lwj
 * @date 2020-10-22
 */
@Slf4j
public class DBPool {

    private static LinkedList<Connection> pool = new LinkedList<>();
    private int initSize;

    public DBPool(int initSize) {
        if (initSize <= 0) {
            throw new IllegalArgumentException("初始连接数必须大于0");
        }

        log.info("初始化连接池，初始连接数量：{}", initSize);
        this.initSize = initSize;
        for (int i = 0; i < initSize; i++) {
            pool.add(new MyConnection());
        }
    }

    public Connection getConnection() throws TimeoutException {
        return getConnection(0);
    }

    public Connection getConnection(long mills) throws TimeoutException {
        if (mills <= 0) {
            // 永不超时
            synchronized (pool) {
                while (pool.isEmpty()) {
                    try {
                        log.info("等待获取连接");
                        pool.wait();
                        log.info("被唤醒");
                    } catch (InterruptedException e) {
                        // 忽略打断
                    }
                }
                return pool.removeFirst();
            }
        } else {
            long timeount = System.currentTimeMillis() + mills;
            long remaining = mills;
            synchronized (pool) {
                while (pool.isEmpty() && remaining > 0) {
                    try {
                        log.info("等待获取连接，超时时间：{}", remaining);
                        pool.wait(remaining);
                        // 被唤醒计算剩余超时时间
                        remaining = timeount - System.currentTimeMillis();
                        log.info("被唤醒");
                    } catch (InterruptedException e) {
                        // 被打断需要计算剩余超时时间
                        remaining = timeount - System.currentTimeMillis();
                        log.info("被打断");
                    }
                }

                if (!pool.isEmpty()) {
                    return pool.removeFirst();
                } else {
                    throw new TimeoutException("超时未获取到连接");
                }
            }
        }
    }

    public void release(Connection connection) {
        if (connection == null) {
            return;
        }

        synchronized (pool) {
            if (pool.size() < initSize) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

}

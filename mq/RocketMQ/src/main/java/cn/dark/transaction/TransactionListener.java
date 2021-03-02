package cn.dark.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 事务消息的监听者，用于生产者发送完消息后执行本地事务
 *
 * @author lwj
 * @date 2021-03-02
 */
public class TransactionListener implements org.apache.rocketmq.client.producer.TransactionListener {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("开始执行本地事务：" + msg.getKeys());
        int status = atomicInteger.incrementAndGet() % 3;
        String transactionId = msg.getTransactionId();
        map.put(transactionId, status);
        switch (status) {
            case 0: // 表示未知事件，由checkLocalTransaction进行处理
                System.out.println("UNKNOW");
                return LocalTransactionState.UNKNOW;
            case 1:
                System.out.println("ROLLBACK_MESSAGE");
                return LocalTransactionState.UNKNOW;
            case 2:
            default:
                System.out.println("COMMIT_MESSAGE");
                return LocalTransactionState.UNKNOW;
        }
    }

    /**
     * 该方法用于RocketMQ与业务确认未提交事务的消息的状态（默认一分钟执行一次，执行5次）
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.out.println("处理未提交的事务：" + msg.getKeys());
        String transactionId = msg.getTransactionId();
        Integer status = map.get(transactionId);
        if (status == null) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        switch (status) {
            case 0:
                System.out.println("ROLLBACK_MESSAGE");
                return LocalTransactionState.ROLLBACK_MESSAGE;
            case 2:
            default:
                System.out.println("COMMIT_MESSAGE");
                return LocalTransactionState.COMMIT_MESSAGE;
        }
    }
}

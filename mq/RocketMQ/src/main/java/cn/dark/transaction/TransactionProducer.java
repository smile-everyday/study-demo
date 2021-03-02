package cn.dark.transaction;

import cn.dark.Constant;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @date 2021-03-02
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        TransactionListener transactionListener = new TransactionListener();

        TransactionMQProducer transactionProducer = new TransactionMQProducer("transaction_group");
        transactionProducer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        // 本地事务监听器，只有本地事务提交后消费者才能消费这条消息
        transactionProducer.setTransactionListener(transactionListener);
        // 处理事务消息的线程池
        transactionProducer.setExecutorService(executor);
        transactionProducer.start();

        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            Message message = new Message("TransactionTopic", tags[i % tags.length], "key" + i, ("transaction demo " + i).getBytes());
            // 发送成功就开始执行本地事务
            TransactionSendResult result = transactionProducer.sendMessageInTransaction(message, null);
            System.out.printf("%s%n", result);
        }

    }
}

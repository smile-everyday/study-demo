package cn.dark.basic.producer;

import cn.dark.Constant;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.CountDownLatch;

/**
 * @author dark
 * @date 2021-02-28
 */
public class AsyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(Constant.PRODUCER_GROUP);
        producer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        producer.start();

        // 设置重试次数，默认是2
        producer.setRetryTimesWhenSendAsyncFailed(2);

        int messageCount = 1;
        CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int ind = i;
            Message message = new Message(Constant.TOPIC, "tagA", "id" + i, ("Hello Rocket " + i).getBytes());
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", ind, new String(message.getBody()));
                }

                @Override
                public void onException(Throwable e) {
                    // 可能会抛出异常，如果是SYSTEM_BUSY，rocketMQ的重试次数会无效；
                    // 但无论如何消息的可靠投递都应该由业务方自己保证
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", ind, e);
                }
            });
        }
        countDownLatch.await();
        System.out.println("消息发送完成");
        producer.getDefaultMQProducerImpl().getAsyncSenderExecutor().shutdown();
        producer.shutdown();
    }

}

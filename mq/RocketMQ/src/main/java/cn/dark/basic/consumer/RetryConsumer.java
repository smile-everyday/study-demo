package cn.dark.basic.consumer;

import cn.dark.Constant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author lwj
 * @date 2021-03-02
 */
public class RetryConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Constant.CONSUMER_GROUP);
        consumer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        consumer.setMaxReconsumeTimes(1);
        consumer.subscribe(Constant.TOPIC, "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("queueID:%d:%s:Messages:%s %n", msgs.get(0).getQueueId(), Thread.currentThread().getName(), new String(msgs.get(0).getBody()));
                // 返回这个，消息会重新发送到broker中的%RETRY%consumer_group，默认重试16次，超过重试次数会进入DLQ，广播模式不能重试
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        consumer.start();
    }

}

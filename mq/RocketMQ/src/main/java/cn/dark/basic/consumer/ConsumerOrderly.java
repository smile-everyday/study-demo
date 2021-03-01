package cn.dark.basic.consumer;

import cn.dark.Constant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author dark
 * @date 2021-03-01
 */
public class ConsumerOrderly {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Constant.CONSUMER_GROUP + "1");
        consumer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        consumer.subscribe(Constant.TOPIC, "*");
        // 从指定时间戳开始消费
        // consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        // 默认就是消费者启动前30分钟开始消费
        // consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis() - (1000 * 60 * 30)));
        System.out.println(UtilAll.timeMillisToHumanString3(System.currentTimeMillis() - (1000 * 60 * 30)));
        /**
         * CONSUME_FROM_FIRST_OFFSET和CONSUME_FROM_LAST_OFFSET区别：
         *     1. 新启动的消费者前者从最早的消息开始消费，后者从最近的消息开始消费（如果有过期消息则忽略，没有则和前者是一样的）
         *     2. 重启后都是从最新的进度开始消费
         *     3. 对于一个新的queue，这个参数无效，都是从0开始消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                System.out.printf("queueID:%d:%s:Messages:%s %n",  msgs.get(0).getQueueId(),Thread.currentThread().getName(), new String(msgs.get(0).getBody()));
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }

}

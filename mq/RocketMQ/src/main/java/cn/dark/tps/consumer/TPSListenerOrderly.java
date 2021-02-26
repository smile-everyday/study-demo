package cn.dark.tps.consumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @date 2021-02-25
 */
public class TPSListenerOrderly implements MessageListenerOrderly {

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        System.out.println(Arrays.toString(msgs.toArray()));
        try {
            /**
             * 当启动消费者大于队列数时，TPS会降低，超出队列数的消费者还会出现不消费消息的现象
             */
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }
}

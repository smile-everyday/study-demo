package cn.dark.tps.consumer;

import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @date 2021-02-25
 */
public class TPSListener implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        try {
            /**
             * 模拟业务操作，经测试业务耗时对TPS影响非常大，当业务耗时在50ms左右时，TPS只能达到400左右，
             * 而当业务耗时在20ms左右时，TPS能达到1800左右（当没有产生竞争时，顺序消费影响不大）
             */
            System.out.println(Arrays.toString(msgs.toArray()));
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

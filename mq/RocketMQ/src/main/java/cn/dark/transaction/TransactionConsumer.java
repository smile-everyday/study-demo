package cn.dark.transaction;

import cn.dark.Constant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @date 2021-03-02
 */
public class TransactionConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction_group");
        consumer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        consumer.subscribe("TransactionTopic", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            private Random random = new Random();

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置自动提交
                context.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    System.out.println("获取到消息开始消费："+msg + " , content : " + new String(msg.getBody()));
                }
                try {
                    // 模拟业务处理
                    TimeUnit.SECONDS.sleep(random.nextInt(5));
                } catch (Exception e) {
                    e.printStackTrace();
                    //返回处理失败，该消息后续可以继续被消费
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                //返回处理成功，该消息就不会再次投递过来了
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }

}

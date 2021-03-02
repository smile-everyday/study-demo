package cn.dark.order;

import cn.dark.Constant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author lwj
 * @date 2021-03-02
 */
public class PartOrderlyProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(Constant.PRODUCER_GROUP);
        producer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        producer.start();

        String[] tags = new String[]{"tagA", "tagB", "tagC", "tagD"};
        for (int i = 0; i < 15; i++) {
            int ind = i % tags.length;
            Message message = new Message(Constant.TOPIC, tags[ind], "KEY" + i, "part orderly demo".getBytes());
            // 使用MessageQueueSelector选择消息发送的分区，确保分区的发送顺序一致
            SendResult send = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    System.out.println("mqs = " + mqs + "，arg = " + arg);
                    return mqs.get(ind);
                }
            }, i);
            System.out.printf("queueID%s %n", send.getMessageQueue().getQueueId() + ":" + message.getTags() + ":" + new String(message.getBody()));
        }

        producer.shutdown();
    }

}

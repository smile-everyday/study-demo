package cn.dark.basic.producer;

import cn.dark.Constant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lwj
 * @date 2021-03-02
 */
public class BatchProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(Constant.PRODUCER_GROUP);
        producer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        producer.start();

        // 批量发送，消息总大小不能大于4M，主题必须相同，不支持延迟
        List<Message> messageList = new LinkedList<>();
        messageList.add(new Message(Constant.TOPIC, "tagA", "1", "batch demo".getBytes()));
        messageList.add(new Message(Constant.TOPIC, "tagB", "2", "batch demo".getBytes()));
        messageList.add(new Message(Constant.TOPIC, "tagC", "3", "batch demo".getBytes()));

        producer.send(messageList);
        producer.shutdown();
    }

}

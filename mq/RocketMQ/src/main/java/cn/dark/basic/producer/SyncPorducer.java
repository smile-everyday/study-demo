package cn.dark.basic.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author lwj
 * @date 2021-02-25
 */
public class SyncPorducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr("192.168.0.179:9876");
        producer.start();

        // 默认是同步发送
        Message message = new Message("ROCKET_TEST", "Hello rocketMQ".getBytes());
        SendResult result = producer.send(message);
        System.out.println(result.toString());

        producer.shutdown();
    }

}

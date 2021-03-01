package cn.dark.basic.producer;

import cn.dark.Constant;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author lwj
 * @date 2021-02-25
 */
public class OnewayProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(Constant.PRODUCER_GROUP);
        producer.setNamesrvAddr("192.168.43.8:9876");
        producer.start();

        // 发送单向消息，不需要响应
        Message message = new Message("oneway", "oneway示例".getBytes());
        producer.sendOneway(message);

        producer.shutdown();
    }

}

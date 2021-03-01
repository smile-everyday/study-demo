package cn.dark.basic.producer;

import cn.dark.Constant;
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
        DefaultMQProducer producer = new DefaultMQProducer(Constant.PRODUCER_GROUP);
        producer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        producer.start();

        // 默认是同步发送，如果需要可靠性保证，业务端需要自己实现
        Message message = new Message(Constant.TOPIC, "Hello rocketMQ".getBytes());
        SendResult result = producer.send(message);
        System.out.println(result.toString());

        producer.shutdown();
    }

}

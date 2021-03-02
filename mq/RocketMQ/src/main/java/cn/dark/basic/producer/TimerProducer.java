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
 * @date 2021-03-02
 */
public class TimerProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(Constant.PRODUCER_GROUP);
        producer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        producer.start();

        Message message = new Message(Constant.TOPIC, "timer demo".getBytes());
        //"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"  18个等级，从1开始
        message.setDelayTimeLevel(2);
        SendResult send = producer.send(message);
        System.out.println(send);
        producer.shutdown();
    }

}

package cn.dark.tps.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @author lwj
 * @date 2021-02-25
 */
public class TPSConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("tpsGroup");
        pushConsumer.setNamesrvAddr("192.168.0.179:9876");
        pushConsumer.subscribe("tpsTest", "*");
        pushConsumer.registerMessageListener(new TPSListenerOrderly());
        pushConsumer.start();
    }

}

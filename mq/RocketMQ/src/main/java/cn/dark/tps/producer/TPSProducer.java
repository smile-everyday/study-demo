package cn.dark.tps.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lwj
 * @date 2021-02-25
 */
public class TPSProducer {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer mqProducer = new DefaultMQProducer("tpsGroup");
        try {
            mqProducer.setNamesrvAddr("192.168.0.179:9876");
            mqProducer.start();

            AtomicLong i = new AtomicLong(0);
            executorService.execute(() -> {
                while (true) {
                    Message message = new Message("tpsTest", ("tpsTest" + i.getAndIncrement()).getBytes());
                    try {
                        mqProducer.sendOneway(message);
                        System.out.println("发送消息成功：" + message.toString());
                    } catch (MQClientException e) {
                        e.printStackTrace();
                    } catch (RemotingException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            mqProducer.shutdown();
        }
    }

}

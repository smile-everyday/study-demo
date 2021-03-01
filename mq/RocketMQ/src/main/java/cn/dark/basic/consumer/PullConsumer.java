package cn.dark.basic.consumer;

import cn.dark.Constant;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author dark
 * @date 2021-03-01
 */
public class PullConsumer {

    private static final Map<MessageQueue, Long> OFFSET = new HashMap<>();

    public static void main(String[] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(Constant.CONSUMER_GROUP);
        consumer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        System.out.println("ms: " + consumer.getBrokerSuspendMaxTimeMillis());
        consumer.start();

        // 拉模式需要自己获取主题的队列进行处理，默认4个
        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues(Constant.TOPIC);
        for (MessageQueue queue : messageQueues) {
            System.out.printf("Consume from the queue: %s%n", queue);

            // 获取偏移量
            consumer.fetchConsumeOffset(queue, true);
            OK:
            while (true) {
                try {
                    PullResult pullResult = consumer.pullBlockIfNotFound(queue, "*", getMessageQueueOffset(queue), 1000);
                    System.out.printf("%s%n", pullResult);
                    putMessageQueueOffset(queue, pullResult.getNextBeginOffset());

                    switch (pullResult.getPullStatus()) {
                        case FOUND: // 读取到消息
                            for (int i = 0; i < pullResult.getMsgFoundList().size(); i++) {
                                System.out.printf("%s%n", new String(pullResult.getMsgFoundList().get(i).getBody()));
                            }
                            System.out.println("FOUND");
                            break;
                        case NO_NEW_MSG: // 没有新消息
                            System.out.println("NO_NEW_MSG");
                            break OK;
                        case NO_MATCHED_MSG: // 没有匹配的消息
                            System.out.println("NO_MATCHED_MSG");
                        case OFFSET_ILLEGAL: // 非法偏移量
                            System.out.println("OFFSET_ILLEGAL");
                        default:
                            System.out.println("default");
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RemotingException e) {
                    e.printStackTrace();
                } catch (MQBrokerException e) {
                    e.printStackTrace();
                }
            }
        }
        consumer.shutdown();
    }

    private static void putMessageQueueOffset(MessageQueue queue, long nextBeginOffset) {
        OFFSET.put(queue, nextBeginOffset);
    }

    private static long getMessageQueueOffset(MessageQueue queue) {
        return OFFSET.get(queue) == null ? 0 : OFFSET.get(queue);
    }

}

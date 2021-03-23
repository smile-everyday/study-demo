package cn.dark.mydemo.tools.forkjoin;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lwj
 * @date 2021-03-16
 */
public class SecretWorkFetch {

    public static void main(String[] args) {
        LinkedBlockingDeque<Work> queue1 = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Work> queue2 = new LinkedBlockingDeque<>();

        new Thread(new ConsumerAndProducer(queue1, queue2)).start();
        new Thread(new ConsumerAndProducer(queue1, queue2)).start();
        new Thread(new ConsumerAndProducer(queue2, queue1)).start();
        new Thread(new ConsumerAndProducer(queue2, queue1)).start();
    }

}

class ConsumerAndProducer implements Runnable {

    private LinkedBlockingDeque<Work> currDeque;
    private LinkedBlockingDeque<Work> otherDeque;

    public ConsumerAndProducer(LinkedBlockingDeque<Work> currDeque, LinkedBlockingDeque<Work> otherDeque) {
        this.currDeque = currDeque;
        this.otherDeque = otherDeque;
    }

    private Work getWork() {
        return new Work();
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Random random = new Random();
            if (random.nextBoolean()) {
                int count = random.nextInt(5);
                for (int i = 0; i < count; i++) {
                    Work work = getWork();
                    work.setPutThread(Thread.currentThread());
                    currDeque.addLast(work);
                }
            }

            if (currDeque.isEmpty()) {
                if (!otherDeque.isEmpty()) {
                    System.out.println("other queue is running!");
                    otherDeque.takeLast().run();
                }
            } else {
                currDeque.takeLast().run();
            }
        }
    }
}

class Work implements Runnable {

    private Thread putThread;
    private static AtomicInteger count = new AtomicInteger(0);

    public Work() {
        count.incrementAndGet();
    }

    @Override
    public void run() {
        if (Thread.currentThread().getId() != putThread.getId()) {
            System.out.println("===============================");
        }
        System.out.println(Thread.currentThread().getId() + ":" + putThread.getId() + "：finish job " + count.get());
    }

    public Thread getPutThread() {
        return putThread;
    }

    public void setPutThread(Thread putThread) {
        this.putThread = putThread;
    }

}

package cn.dark.mydemo.tools.pool;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lwj
 * @date 2020-11-11
 */
public class CompletionServiceDemo {

    private static AtomicInteger count = new AtomicInteger(0);
    private static ExecutorService executorService = Executors.newFixedThreadPool(6, work -> {
        return new Thread(work, "t-" + count.incrementAndGet());
    });
    private static CompletionService<String> completionService = new ExecutorCompletionService(executorService);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < 12; i++) {
            completionService.submit(new WrokTask());
        }

        // 先完成的先打印结果，如果需要按照先入队的先打印结果则需要保存上面submit的返回值，并for循环调用get方法
        for (int i = 0; i < 12; i++) {
            System.out.println(completionService.take().get());
        }

        executorService.shutdown();
    }

    private static class WrokTask implements Callable<String> {
        Random random = new Random();

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            int sec = random.nextInt(10);
            TimeUnit.SECONDS.sleep(sec);
            return Thread.currentThread().getName() + "执行完成，执行耗时：" + sec;
        }
    }
}

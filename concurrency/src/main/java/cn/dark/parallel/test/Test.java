package cn.dark.parallel.test;

import cn.dark.parallel.framework.JobInfo;
import cn.dark.parallel.framework.JobPool;
import cn.dark.parallel.framework.TaskResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @date 2020-11-06
 */
@Slf4j
public class Test {

    private static final String JOB_NAME = "计算任务";
    private static final int JOB_LENGTH = 1000;

    private static class QueryTask implements Runnable {

        private static JobPool jobPool = JobPool.getInstance();

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                List<TaskResult<String>> taskResults = jobPool.getTaskResults(JOB_NAME);
                if (!taskResults.isEmpty()) {
                    log.info(jobPool.getTaskProgress(JOB_NAME));
                    log.info("任务结果：{}", taskResults);
                }

                JobInfo<Integer> jobInfo = jobPool.getJob(JOB_NAME);
                if (jobInfo.getTaskProcessCount() == JOB_LENGTH) {
                    break;
                }

                TimeUnit.MILLISECONDS.sleep(100);
            }
        }
    }

    public static void main(String[] args) {
        MyTaskProcessor myTaskProcessor = new MyTaskProcessor();
        JobPool jobPool = JobPool.getInstance();
        jobPool.registerJob(JOB_NAME, JOB_LENGTH, myTaskProcessor, 5);

        Random random = new Random();
        for (int i = 0; i < JOB_LENGTH; i++) {
            jobPool.putTask(JOB_NAME, random.nextInt(1000));
        }

        new Thread(new QueryTask()).start();
    }

}

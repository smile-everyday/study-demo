package cn.dark.parallel.framework;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * @author lwj
 * @date 2020-11-06
 */
@Slf4j
public class JobExpireChecker {

    private static DelayQueue<JobDelayVO> delayQueue = new DelayQueue<>();

    private JobExpireChecker() {}

    private static class JobExpireCheckerHolder {
        private static JobExpireChecker jobExpireChecker = new JobExpireChecker();
    }

    public static JobExpireChecker getInstance() {
        return JobExpireCheckerHolder.jobExpireChecker;
    }

    private static class FetchJob implements Runnable {

        private static DelayQueue<JobDelayVO> delayQueue = JobExpireChecker.delayQueue;
        private static Map<String, JobInfo<?>> jobCache = JobPool.getJobCache();

        @Override
        public void run() {
            while (true) {
                try {
                    JobDelayVO jobDelayVO = delayQueue.take();
                    if (jobDelayVO == null) {
                        continue;
                    }

                    jobCache.remove(jobDelayVO.getJobName());
                    log.info("任务{}过期，删除缓存", jobDelayVO.getJobName());
                } catch (InterruptedException e) {
                    log.error("删除缓存出错：{}", e);
                }
            }
        }
    }

    public void putJob(String jobName, long expire) {
        JobDelayVO delayVO = new JobDelayVO(expire, jobName);
        delayQueue.offer(delayVO);
        log.info("任务{}已放入过期检查队列", jobName);
    }

    static {
        Thread thread = new Thread(new FetchJob());
        thread.setDaemon(true);
        thread.start();
        log.info("开启过期检查守护线程");
    }

}

package cn.dark.parallel.framework;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author dark
 * @date 2020-11-05
 */
public class JobPool {

    private static final Integer THREAD_COUNT = Runtime.getRuntime().availableProcessors() + 1;
    private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);
    private static ExecutorService taskExecutor = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT, 60, TimeUnit.SECONDS, taskQueue);
    private static Map<String, JobInfo> jobCache = new ConcurrentHashMap<>();

    private JobPool() {}

    private static class JobPoolHolder {
        private static JobPool jobPool = new JobPool();
    }

    public static JobPool getInstance() {
        return JobPoolHolder.jobPool;
    }

    public Map<String, JobInfo> getJobCache() {
        return jobCache;
    }

    private static class Task<T, R> implements Runnable {

        private JobInfo<R> jobInfo;
        private T data;

        public Task(JobInfo<R> jobInfo, T data) {
            this.jobInfo = jobInfo;
            this.data = data;
        }

        @Override
        public void run() {
            R r = null;
            ITaskProcessor<T, R> iTaskProcessor = (ITaskProcessor<T, R>) jobInfo.getTaskProcessor();
            TaskResult<R> result = null;
            try {
                result = iTaskProcessor.execute(data);
                if (result == null) {
                    result = new TaskResult<>(TaskResult.ResultEnum.EXCEPTION, r, "result is null");
                }

                if (result.getResultType() == null) {
                    if (result.getMsg() == null) {
                        result = new TaskResult<>(TaskResult.ResultEnum.EXCEPTION, r, "result is null");
                    } else {
                        result = new TaskResult<>(TaskResult.ResultEnum.EXCEPTION, r, "result is null, reason: " + result.getMsg());
                    }
                }
            } catch (Exception e) {
                result = new TaskResult<>(TaskResult.ResultEnum.EXCEPTION, r, e.getMessage());
            }

            jobInfo.addResult(result);
        }

    }

    public <R> void registerJob(String jobName, int jobLength, ITaskProcessor iTaskProcessor, long expireTime) {
        JobInfo<R> jobInfo = new JobInfo<>(jobName, jobLength, iTaskProcessor, expireTime);
        if (jobCache.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException("任务【" + jobName + "】已经存在");
        }
    }

    public <T, R> void putTask(String jobName, T t) {
        JobInfo<R> jobInfo = getJob(jobName);
        Task<T, R> task = new Task<>(jobInfo, t);
        taskExecutor.execute(task);
    }

    public <R> List<TaskResult<R>> getTaskResults(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTaskResults();
    }

    public String getTaskProgress(String jobName) {
        JobInfo<Object> info = getJob(jobName);
        return info.getTaskProgress();
    }

    private <R> JobInfo<R> getJob(String jobName) {
        JobInfo jobInfo = jobCache.get(jobName);
        if (jobInfo == null) {
            throw new RuntimeException("任务【" + jobName + "】不存在");
        }
        return jobInfo;
    }

}

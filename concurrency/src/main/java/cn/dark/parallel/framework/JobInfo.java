package cn.dark.parallel.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dark
 * @date 2020-11-05
 */
public class JobInfo<R> {

    private String name;
    private int jobLength;
    private final long expireTime;
    private ITaskProcessor<?, ?> iTaskProcessor;

    private AtomicInteger successCount;
    private AtomicInteger taskProcessCount;

    private LinkedBlockingDeque<TaskResult<R>> taskResults;

    public JobInfo(String name, int jobLength, ITaskProcessor<?, ?> iTaskProcessor, long expireTime) {
        this.name = name;
        this.jobLength = jobLength;
        this.iTaskProcessor = iTaskProcessor;
        this.expireTime = expireTime;
        this.successCount = new AtomicInteger(0);
        this.taskProcessCount = new AtomicInteger(0);
        this.taskResults = new LinkedBlockingDeque<>(jobLength);
    }

    public ITaskProcessor<?, ?> getTaskProcessor() {
        return iTaskProcessor;
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getTaskProcessCount() {
        return taskProcessCount.get();
    }

    public int getFailCount() {
        return getTaskProcessCount() - getSuccessCount();
    }

    public List<TaskResult<R>> getTaskResults () {
        List<TaskResult<R>> results = new ArrayList<>();
        TaskResult<R> result;
        while ((result = taskResults.pollFirst()) != null) {
            results.add(result);
        }
        return results;
    }

    public String getTaskProgress() {
        return "任务总数：" + jobLength + "，已处理：" + getTaskProcessCount() + "，处理成功：" + getSuccessCount();
    }

    public void addResult(TaskResult<R> result) {
        taskProcessCount.incrementAndGet();
        if (result.getResultType() == TaskResult.ResultEnum.SUCCESS) {
            successCount.incrementAndGet();
        }

        taskResults.addLast(result);
        // todo 检查线程
    }
}

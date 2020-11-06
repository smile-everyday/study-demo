package cn.dark.parallel.framework;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @date 2020-11-06
 */
public class JobDelayVO implements Delayed {

    private long expireTime;
    private String jobName;

    public JobDelayVO(long expireTime, String jobName) {
        this.expireTime = expireTime * 1000 + System.currentTimeMillis();
        this.jobName = jobName;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public String getJobName() {
        return jobName;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        if (d < 0) {
            return -1;
        } else if (d > 0) {
            return 1;
        }
        return 0;
    }
}

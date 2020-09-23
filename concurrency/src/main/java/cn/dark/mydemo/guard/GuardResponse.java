package cn.dark.mydemo.guard;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dark
 * @date 2020-09-23
 */
@Slf4j
public class GuardResponse {

    private Object data;

    public Object getData() throws InterruptedException {
        if (data != null) {
            return data;
        }

        synchronized (this) {
            while (data == null) {
                this.wait();
            }
        }
        return data;
    }

    public Object getData(int mills) throws InterruptedException {
        if (data != null) {
            return data;
        }

        long begin = System.currentTimeMillis();
        long timePassed = 0;
        synchronized (this) {
            while (data == null) {
                long wait = mills - timePassed;
                if (wait <= 0) {
                    break;
                }
                this.wait(wait);
                timePassed = System.currentTimeMillis() - begin;
            }
        }
        return data;
    }

    public void setData(Object data) {
        synchronized (this) {
            this.data = data;
            this.notifyAll();
        }
    }

}

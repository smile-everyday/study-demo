package cn.dark.parallel.test;

import cn.dark.parallel.framework.ITaskProcessor;
import cn.dark.parallel.framework.TaskResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @date 2020-11-06
 */
@Slf4j
public class MyTaskProcessor implements ITaskProcessor<Integer, Integer> {

    @Override
    public TaskResult<Integer> execute(Integer data) {
        Random random = new Random();
        int i = random.nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (i < 300) {
            return new TaskResult<>(TaskResult.ResultEnum.SUCCESS, i + data, "");
        } else if (i >= 300 && i < 600) {
            return new TaskResult<>(TaskResult.ResultEnum.FAIL, null, "计算错误");
        } else {
            throw new RuntimeException("异常发生了");
        }
    }

}

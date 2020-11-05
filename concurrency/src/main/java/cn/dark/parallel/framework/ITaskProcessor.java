package cn.dark.parallel.framework;

/**
 * @author dark
 * @date 2020-11-05
 */
public interface ITaskProcessor<T, R> {

    TaskResult<R> execute(T data);

}

package cn.dark.parallel.framework;

/**
 * @author dark
 * @date 2020-11-05
 */
public class TaskResult<T> {

    private ResultEnum resultType;
    private T result;
    private String msg;

    public TaskResult(ResultEnum resultType, T result, String msg) {
        this.resultType = resultType;
        this.result = result;
        this.msg = msg;
    }

    public ResultEnum getResultType() {
        return resultType;
    }

    public T getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public enum ResultEnum {
        SUCCESS, FAIL, EXCEPTION;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "resultType=" + resultType +
                ", result=" + result +
                ", msg='" + msg + '\'' +
                '}';
    }
}

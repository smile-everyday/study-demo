package cn.dark.observer;

/**
 * @author dark
 * @date 2018-11-17
 */
public interface Observer {

    void update(Subject subject, Object arg);

}

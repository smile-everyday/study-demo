package cn.dark.state;

/**
 * @author dark
 * @date 2019-02-06
 */
public interface State {

    void accept(int count);

    void move();

    void attack();

    void submit();

}

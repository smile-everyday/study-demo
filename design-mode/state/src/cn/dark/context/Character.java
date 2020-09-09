package cn.dark.context;

import cn.dark.state.*;

/**
 * @author dark
 * @date 2019-02-06
 */
public class Character {

    // 当前状态
    private State current = new StopState(this);
    // 所需杀怪数量
    private int count = 0;

    public void accept(int count) {
        // 注意这里不能直接将值赋给成员变量
        current.accept(count);
    }

    public void move() {
        current.move();
    }

    public void attack() {
        current.attack();
    }

    public void submit() {
        current.submit();
    }

    public void killOne() {
        this.count--;
    }

    public void setCurrent(State current) {
        this.current = current;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getCurrent() {
        return current;
    }

    public int getCount() {
        return count;
    }
}

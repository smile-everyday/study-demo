package cn.dark.state;

import cn.dark.context.Character;

/**
 * @author dark
 * @date 2019-02-06
 */
public class StopState implements State {
    private Character c;

    public StopState(Character c) {
        this.c = c;
    }

    @Override
    public void accept(int count) {
        c.setCount(count);
        c.setCurrent(new NoMonsterState(c));
        c.move();
    }

    @Override
    public void move() {
        System.out.println("Moving....");
        c.setCurrent(new HasMonsterState(c));
        c.attack();
    }

    @Override
    public void attack() {
        System.out.println("Sorry!You must accept the task!");
    }

    @Override
    public void submit() {
        System.out.println("You don't have task to submit!");
    }
}

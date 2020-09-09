package cn.dark.state;

import cn.dark.context.Character;

/**
 * @author dark
 * @date 2019-02-06
 */
public class NoMonsterState implements State {
    private Character c;

    public NoMonsterState(Character c) {
        this.c = c;
    }

    @Override
    public void accept(int count) {
        System.out.println("Sorry!You are doing the task,so you can't accept the new task!!");
    }

    @Override
    public void move() {
        System.out.println("Moving to find monster!");
        c.setCurrent(new HasMonsterState(c));
        c.attack();
    }

    @Override
    public void attack() {
        c.move();
    }

    @Override
    public void submit() {
        System.out.println("Please complete the task!");
    }
}

package cn.dark.state;

import cn.dark.context.Character;

/**
 * @author dark
 * @date 2019-02-06
 */
public class HasMonsterState implements State {
    private Character c;

    public HasMonsterState(Character c) {
        this.c = c;
    }

    @Override
    public void accept(int count) {
        System.out.println("Sorry!You are doing the task,so you can't accept the new task!!");
    }

    @Override
    public void move() {
        System.out.println("Moving to find new monster!");
        c.attack();
    }

    @Override
    public void attack() {
        while (c.getCount() > 0) {
            c.killOne();
            System.out.println("need to kill:" + c.getCount());
        }
        c.setCurrent(new MissionClearState(c));
        c.move();
    }

    @Override
    public void submit() {
        System.out.println("Please complete the task!");
    }
}

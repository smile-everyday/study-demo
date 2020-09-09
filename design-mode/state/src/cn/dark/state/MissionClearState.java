package cn.dark.state;

import cn.dark.context.Character;

/**
 * @author dark
 * @date 2019-02-06
 */
public class MissionClearState implements State {
    private Character c;

    public MissionClearState(Character c) {
        this.c = c;
    }

    @Override
    public void accept(int count) {
        System.out.println("Sorry!You must submit the current task!");
    }

    @Override
    public void move() {
        System.out.println("Moving to submit!");
        c.submit();
    }

    @Override
    public void attack() {
        System.out.println("Your task is completed!");
        c.move();
    }

    @Override
    public void submit() {
        System.out.println("Congratulations on completing the task!");
        c.setCurrent(new StopState(c));
    }
}

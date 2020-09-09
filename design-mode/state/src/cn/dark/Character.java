package cn.dark;

/**
 * @author dark
 * @date 2019-02-06
 */
public class Character {

    // 停止
    private final static int STOP = 0;
    // 附近有怪
    private final static int HASMONSTER = 1;
    // 附近没有怪
    private final static int NOMONSTER = 2;
    // 任务条件达成
    private final static int MISSIONCLEAR = 4;

    // 当前状态
    private int state = STOP;
    // 还需杀怪数量
    private int count = 0;

    public void accept(int count) {
        if (state == STOP) {
            System.out.println("Accept the task.Need to kill monster:" + count);
            this.count = count;
            state = NOMONSTER;
            // move to find the monster
            move();
        } else if (state == HASMONSTER) {
            System.out.println("Sorry!You are doing the task,so you can't accept the new task!!");
        } else if (state == NOMONSTER) {
            System.out.println("Sorry!You are doing the task,so you can't accept the new task!!");
        } else if (state == MISSIONCLEAR) {
            System.out.println("Sorry!You must submit the current task!");
        }
    }

    private void move() {
        if (state == STOP) {
            System.out.println("Moving....");
            state = HASMONSTER;
            attack();
        } else if (state == HASMONSTER) {
            System.out.println("Moving to find new monster");
            attack();
        } else if (state == NOMONSTER) {
            System.out.println("Moving to find monster");
            state = HASMONSTER;
            attack();
        } else if (state == MISSIONCLEAR) {
            System.out.println("Moving to submit");
            submit();
        }
    }

    private void attack() {
        if (state == STOP) {
            System.out.println("Sorry!You must accept the task!");
        } else if (state == HASMONSTER) {
            count--;
            System.out.println("need to kill:" + count);
            if (count > 0) {
                attack();
            } else {
                state = MISSIONCLEAR;
                move();
            }
        } else if (state == NOMONSTER) {
            move();
        } else if (state == MISSIONCLEAR) {
            move();
        }
    }

    private void submit() {
        if (state == STOP) {
            System.out.println("You don't have task to submit!");
        } else if (state == HASMONSTER) {
            System.out.println("Please complete the task!");
        } else if (state == NOMONSTER) {
            System.out.println("Please complete the task!");
        } else if (state == MISSIONCLEAR) {
            System.out.println("Congratulations on completing the task!");
            state = STOP;
        }
    }

}

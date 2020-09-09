package cn.dark.strategy;

/**
 * @author dark
 * @date 2018-11-03
 */
public class SwordBehavior implements WeaponBehavior {

    @Override
    public void userWeap() {
        System.out.println("用宝剑挥舞！");
    }

}

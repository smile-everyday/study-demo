package cn.dark.strategy;

/**
 * @author dark
 * @date 2018-11-03
 */
public class MainClass {

    public static void main(String[] args) {
        Character c = new King(new AxeBehavior());
        c.fight();
        c.setWeaponBehavior(new SwordBehavior());
        c.fight();
    }

}

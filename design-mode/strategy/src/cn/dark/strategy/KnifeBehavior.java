package cn.dark.strategy;

/**
 * @author dark
 * @date 2018-11-03
 */
public class KnifeBehavior implements WeaponBehavior {

    @Override
    public void userWeap() {
        System.out.println("用匕首刺杀");
    }

}

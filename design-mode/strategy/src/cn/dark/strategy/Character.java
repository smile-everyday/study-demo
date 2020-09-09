package cn.dark.strategy;

/**
 * @author dark
 * @date 2018-11-03
 */
public abstract class Character {

    WeaponBehavior weaponBehavior;

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    public void fight() {
        weaponBehavior.userWeap();
    }

}

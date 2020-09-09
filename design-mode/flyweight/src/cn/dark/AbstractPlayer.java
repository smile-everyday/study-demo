package cn.dark;

/**
 * @author dark
 * @date 2019-02-12
 */
public abstract class AbstractPlayer {

    private String weapon;
    protected String mission;


    public void assignWeapon(String weapon) {
        System.out.println("使用武器：" + weapon);
        this.weapon = weapon;
    }

    public void execute() {
        System.out.println("execute mission: " + mission);
    }
}

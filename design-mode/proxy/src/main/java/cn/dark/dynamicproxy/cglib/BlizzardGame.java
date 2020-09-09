package cn.dark.dynamicproxy.cglib;

/**
 * @Title: RealSubject as Game.
 * @Author: Dark
 * @Date: 2018-08-25
 */
public class BlizzardGame {

    /**
     * Publicize by Blizzard
     *
     *@date：2018-08-25
     *
     */
    public void publicize() {
        System.out.println("Game is publicizing by Blizzard!");
    }

    /**
     * Operate by Blizzard
     *
     *@date：2018-08-25
     *
     */
    public void operate() {
        System.out.println("Game is operating by Blizzard!");
    }

    /**
     * Publicize by others
     *
     *@param：companName
     *@date：2018-08-25
     *
     */
    public void publicize(String companyName) {
        System.out.println("Game is publicizing " + companyName + "!");
    }

    /**
     * Operate by others
     *
     *@param：companName
     *@date：2018-08-25
     *
     */
    public void operate(String compantName) {
        System.out.println("Game is operating by " + compantName + "!");
    }

}

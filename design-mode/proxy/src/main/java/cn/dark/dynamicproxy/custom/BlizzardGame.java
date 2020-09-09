package cn.dark.dynamicproxy.custom;

/**
 * @Title: RealSubject as Game.
 * @Author: Dark
 * @Date: 2018-08-25
 */
public class BlizzardGame implements Game {

    /**
     * Publicize by others
     *
     *@param：companName
     *@date：2018-08-25
     *
     */
    @Override
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
    @Override
    public void operate(String compantName) {
        System.out.println("Game is operating by " + compantName + "!");
    }

}

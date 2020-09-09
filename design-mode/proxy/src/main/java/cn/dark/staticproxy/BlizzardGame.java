package cn.dark.staticproxy;

/**
 * @Title: RealSubject as Game.
 * @Author: Dark
 * @Date: 2018-08-25
 */
public class BlizzardGame implements Game {

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

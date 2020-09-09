package cn.dark.staticproxy;

/**
 * @Title: Agent
 * @Author: Dark
 * @Date: 2018-08-25
 */
public class NineCity implements Game {

    /**
     * Real-subject's reference
     */
    private Game game;

    public NineCity(Game game) {
        this.game = game;
    }

    /**
     * Operate by agent, Agent can add any features on after or before operate by himself
     *
     *@param：companyName
     *@date：2018-08-25
     *
     */
    @Override
    public void operate(String companyName) {
        System.out.println("Localizate...");
        game.operate(companyName);
        System.out.println("Online activities...");
    }

}

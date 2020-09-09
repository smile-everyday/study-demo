package cn.dark.dynamicproxy.jdk;

/**
 * @Title: Game is subject-interface, real-subject and proxy-subject need to implements it.
 * @Author: Dark
 * @Date: 2018-08-25
 */
public interface Game {

    /**
     * Game publicize
     *
     *@date：2018-08-25
     *
     */
    void publicize(String companyName);

    /**
     * Game start operate
     *
     *@date：2018-08-25
     *
     */
    void operate(String companyName);

}

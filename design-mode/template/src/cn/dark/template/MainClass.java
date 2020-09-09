package cn.dark.template;

/**
 * @author dark
 * @date 2018-10-20
 */
public class MainClass {

    public static void main(String[] args) {
        Vegetables v = new Potato();
        v.doCooking();

        v = new Cucumber();
        v.doCooking();
    }

}

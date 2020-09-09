package cn.dark.simplefactory;

/**
 * @author dark
 * @date 2018-10-03
 */
public class MainClass {

    public static void main(String[] args) throws Exception {
        Car benz = CarFactory.getCar2("cn.dark.simplefactory.BenzCar");
        benz.makeHead();
        benz.makeBody();
        benz.makeTail();
        benz.assemble();
    }

}

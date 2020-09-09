package cn.dark.abstractfactory.product;

/**
 * real product
 *
 * @author dark
 * @date 2018-10-03
 */
public class BenzCar extends Car {

    @Override
    public void makeHead() {
        System.out.println("BenzCar's head is created!");
    }

    @Override
    public void makeBody() {
        System.out.println("BenzCar's body is created!");
    }

    @Override
    public void makeTail() {
        System.out.println("Bean's tail is created!");
    }
}

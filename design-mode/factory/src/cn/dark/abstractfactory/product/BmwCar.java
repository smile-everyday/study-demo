package cn.dark.abstractfactory.product;

/**
 * real product
 *
 * @author dark
 * @date 2018-10-03
 */
public class BmwCar extends Car {

    @Override
    public void makeHead() {
        System.out.println("BmwCar's head is created!");
    }

    @Override
    public void makeBody() {
        System.out.println("BmwCar's body is created!");
    }

    @Override
    public void makeTail() {
        System.out.println("BmwCar's tail is created!");
    }
}

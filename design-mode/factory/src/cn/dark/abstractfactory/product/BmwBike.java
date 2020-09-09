package cn.dark.abstractfactory.product;

/**
 * @author dark
 * @date 2018-10-03
 */
public class BmwBike extends Bike {

    @Override
    public void makeHead() {
        System.out.println("BmwBike's head is created!");
    }

    @Override
    public void makeBody() {
        System.out.println("BmwBike's body is created!");
    }

    @Override
    public void makeTail() {
        System.out.println("BmwBike's tail is created!");
    }
}

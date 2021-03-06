package cn.dark.abstractfactory.product;

/**
 * @author dark
 * @date 2018-10-03
 */
public class BenzBike extends Bike {


    @Override
    public void makeHead() {
        System.out.println("BenzBike's head is created!");
    }

    @Override
    public void makeBody() {
        System.out.println("BenzBike's body is created!");
    }

    @Override
    public void makeTail() {
        System.out.println("BenzBike's tail is created!");
    }
}

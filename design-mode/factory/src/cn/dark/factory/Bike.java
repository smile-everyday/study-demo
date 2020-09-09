package cn.dark.factory;

/**
 * real product
 *
 * @author dark
 * @date 2018-10-03
 */
public class Bike extends Vehicle {

    @Override
    void makeHead() {
        System.out.println("Bike's head is created!");
    }

    @Override
    void makeBody() {
        System.out.println("Bike's body is created!");
    }

    @Override
    void makeTail() {
        System.out.println("Bike's tail is created!");
    }
}

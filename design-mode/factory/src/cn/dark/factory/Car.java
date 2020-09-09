package cn.dark.factory;

/**
 * real product
 *
 * @author dark
 * @date 2018-10-03
 */
public class Car extends Vehicle {

    @Override
    void makeHead() {
        System.out.println("Car's head is created!");
    }

    @Override
    void makeBody() {
        System.out.println("Car's body is created!");
    }

    @Override
    void makeTail() {
        System.out.println("Car's tail is created!");
    }
}

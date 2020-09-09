package cn.dark.simplefactory;

/**
 * real product
 *
 * @author dark
 * @date 2018-10-03
 */
public class Bmw extends Car {

    @Override
    void makeHead() {
        System.out.println("BmwCar's head is created!");
    }

    @Override
    void makeBody() {
        System.out.println("BmwCar's body is created!");
    }

    @Override
    void makeTail() {
        System.out.println("BmwCar's tail is created!");
    }
}

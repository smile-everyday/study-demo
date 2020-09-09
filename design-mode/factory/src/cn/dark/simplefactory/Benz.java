package cn.dark.simplefactory;

/**
 * real product
 *
 * @author dark
 * @date 2018-10-03
 */
public class Benz extends Car {

    @Override
    void makeHead() {
        System.out.println("BenzCar's head is created!");
    }

    @Override
    void makeBody() {
        System.out.println("BenzCar's body is created!");
    }

    @Override
    void makeTail() {
        System.out.println("Bean's tail is created!");
    }
}

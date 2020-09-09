package cn.dark.abstractfactory;

import cn.dark.abstractfactory.creator.BenzFactory;
import cn.dark.abstractfactory.creator.BmwFactory;
import cn.dark.abstractfactory.creator.Factory;
import cn.dark.abstractfactory.product.Bike;
import cn.dark.abstractfactory.product.Car;

/**
 * @author dark
 * @date 2018-10-03
 */
public class MainClass {

    public static void main(String[] args) {
        Factory factory = new BenzFactory();
        Bike bike = factory.getBike();
        Car car = factory.getCar();

        factory = new BmwFactory();
        Bike bike1 = factory.getBike();
        Car car1 = factory.getCar();
    }

}

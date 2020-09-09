package cn.dark.abstractfactory.creator;

import cn.dark.abstractfactory.product.BenzBike;
import cn.dark.abstractfactory.product.BenzCar;
import cn.dark.abstractfactory.product.Bike;
import cn.dark.abstractfactory.product.Car;

/**
 * @author dark
 * @date 2018-10-03
 */
public class BenzFactory implements Factory {

    @Override
    public Car getCar() {
        return new BenzCar();
    }

    @Override
    public Bike getBike() {
        return new BenzBike();
    }
}

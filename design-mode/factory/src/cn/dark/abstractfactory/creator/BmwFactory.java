package cn.dark.abstractfactory.creator;

import cn.dark.abstractfactory.product.Bike;
import cn.dark.abstractfactory.product.BmwBike;
import cn.dark.abstractfactory.product.BmwCar;
import cn.dark.abstractfactory.product.Car;

/**
 * @author dark
 * @date 2018-10-03
 */
public class BmwFactory implements Factory {

    @Override
    public Car getCar() {
        return new BmwCar();
    }

    @Override
    public Bike getBike() {
        return new BmwBike();
    }
}

package cn.dark.abstractfactory.creator;

import cn.dark.abstractfactory.product.Bike;
import cn.dark.abstractfactory.product.Car;

/**
 * @author dark
 * @date 2018-10-03
 */
public interface Factory {

    Car getCar();

    Bike getBike();

}

package cn.dark.factory;

/**
 * BmwCar's factory
 *
 * @author dark
 * @date 2018-10-03
 */
public class CarFactory implements Factory {

    @Override
    public Vehicle getCar() {
        return new Car();
    }

}

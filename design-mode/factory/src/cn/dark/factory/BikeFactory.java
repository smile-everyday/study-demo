package cn.dark.factory;

/**
 * BenzCar's factory
 *
 * @author dark
 * @date 2018-10-03
 */
public class BikeFactory implements Factory {

    @Override
    public Vehicle getCar() {
        return new Bike();
    }

}

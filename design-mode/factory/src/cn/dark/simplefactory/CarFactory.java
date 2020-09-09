package cn.dark.simplefactory;

import cn.dark.simplefactory.consts.CarType;

/**
 * Creator of car
 *
 * @author dark
 * @date 2018-10-03
 */
public class CarFactory {

    /**
     * Get a car by type
     *
     *@param type
     *@return Vehicle
     *@throws Exception
     *@date 2018-10-03
     *
     */
    public static Car getCar(String type) throws Exception {
        if (CarType.BENZ.equals(type)) {
            return new Benz();
        } else if (CarType.BMW.equals(type)) {
            return new Bmw();
        } else {
            throw new Exception("No such car!");
        }

    }

    /**
     * Get a car by className
     *
     *@param className
     *@return Vehicle
     *@throws Exception
     *@date 2018-10-03
     *
     */
    public static Car getCar2(String className) throws Exception {
        Class<?> clzz = Class.forName(className);

        return (Car) clzz.newInstance();

    }

}

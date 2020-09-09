package cn.dark.decorator.concretecomponent;

import cn.dark.decorator.Beverage;

/**
 * @author dark
 * @date 2018-11-23
 */
public class MilkTea implements Beverage {

    @Override
    public double cost() {
        return 5;
    }

}

package cn.dark.decorator.concretecomponent;

import cn.dark.decorator.Beverage;

/**
 * @author dark
 * @date 2018-11-25
 */
public class Coffe implements Beverage {

    @Override
    public double cost() {
        return 20;
    }

}

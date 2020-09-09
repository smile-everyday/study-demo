package cn.dark.decorator.concretedecorator;

import cn.dark.decorator.Beverage;
import cn.dark.decorator.Decorator;

/**
 * @author dark
 * @date 2018-11-23
 */
public class Coconut extends Decorator {

    public Coconut(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 4.3;
    }
}

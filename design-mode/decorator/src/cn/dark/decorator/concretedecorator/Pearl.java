package cn.dark.decorator.concretedecorator;

import cn.dark.decorator.Beverage;
import cn.dark.decorator.Decorator;

/**
 * @author dark
 * @date 2018-11-23
 */
public class Pearl extends Decorator {

    public Pearl(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 2.2;
    }
}

package cn.dark.decorator.concretedecorator;

import cn.dark.decorator.Beverage;
import cn.dark.decorator.Decorator;

/**
 * @author dark
 * @date 2018-11-25
 */
public class Sugar extends Decorator {

    public Sugar(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 1;
    }
}

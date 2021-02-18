package cn.dark.decoratorFactory;

import cn.dark.decorator.Beverage;
import cn.dark.decorator.concretecomponent.Coffe;
import cn.dark.decorator.concretedecorator.Milk;
import cn.dark.decorator.concretedecorator.Sugar;

/**
 * @author dark
 * @date 2018-11-25
 */
public class MilkSugarCoffeFactory implements Factory {
    @Override
    public Beverage getBeverage() {
        return new Milk(new Sugar(new Coffe()));
    }
}

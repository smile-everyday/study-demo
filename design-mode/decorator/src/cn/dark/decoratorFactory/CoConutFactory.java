package cn.dark.decoratorFactory;

import cn.dark.decorator.Beverage;
import cn.dark.decorator.concretedecorator.Coconut;
import cn.dark.decorator.concretecomponent.MilkTea;

/**
 * @author dark
 * @date 2018-11-25
 */
public class CoConutFactory implements Factory {

    @Override
    public Beverage getBeverage() {
        return new Coconut(new MilkTea());
    }

}

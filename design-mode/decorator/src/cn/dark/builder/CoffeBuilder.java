package cn.dark.builder;

import cn.dark.decorator.Beverage;
import cn.dark.decorator.concretecomponent.Coffe;

/**
 * @author dark
 * @date 2018-11-25
 */
public abstract class CoffeBuilder {

    protected Beverage beverage = new Coffe();

    abstract void addMilk();

    abstract void addSugar();

    public Beverage getBeverage() {
        return beverage;
    }

}

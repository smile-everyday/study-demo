package cn.dark.builder;

import cn.dark.decorator.concretedecorator.Milk;
import cn.dark.decorator.concretedecorator.Sugar;

/**
 * @author dark
 * @date 2018-11-25
 */
public class MilkSugarCoffeCoffeBuilder extends CoffeBuilder {

    @Override
    public void addMilk() {
        beverage = new Milk(beverage);
    }

    @Override
    public void addSugar() {
        beverage = new Sugar(beverage);
    }

}

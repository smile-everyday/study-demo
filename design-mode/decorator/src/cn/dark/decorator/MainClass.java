package cn.dark.decorator;

import cn.dark.builder.CoffeBuilder;
import cn.dark.builder.Designer;
import cn.dark.builder.MilkSugarCoffeCoffeBuilder;
import cn.dark.decorator.concretecomponent.MilkTea;
import cn.dark.decorator.concretedecorator.Coconut;
import cn.dark.decorator.concretedecorator.Pearl;

/**
 * @author dark
 * @date 2018-11-23
 */
public class MainClass {

    public static void main(String[] args) {
        Beverage beverage = new MilkTea();
        Decorator decorator = new Pearl(beverage);
        System.out.println(decorator.cost());
        Decorator decorator1 = new Coconut(decorator);
        System.out.println(decorator1.cost());

        CoffeBuilder coffeBuilder = new MilkSugarCoffeCoffeBuilder();
        Designer designer = new Designer(coffeBuilder);
        designer.build();
        Beverage beverage1 = coffeBuilder.getBeverage();
        System.out.println(beverage1.cost());
    }

}

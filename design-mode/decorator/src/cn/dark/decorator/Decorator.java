package cn.dark.decorator;

/**
 * @author dark
 * @date 2018-11-23
 */
public abstract class Decorator implements Beverage {

    protected Beverage beverage;

    public Decorator(Beverage beverage) {
        this.beverage = beverage;
    }

}

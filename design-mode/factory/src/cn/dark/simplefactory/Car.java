package cn.dark.simplefactory;

/**
 * abstract product
 *
 * @author dark
 * @date 2018-10-03
 */
public abstract class Car {

    abstract void makeHead();

    abstract void makeBody();

    abstract void makeTail();

    public void assemble() {
        System.out.println("Assemble all concretecomponent to car!");
    }

}

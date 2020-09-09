package cn.dark.abstractfactory.product;

/**
 * abstract product
 *
 * @author dark
 * @date 2018-10-03
 */
public abstract class Car implements Vehicle {

    @Override
    public void assemble() {
        System.out.println("Assemble all concretecomponent to car!");
    }

}

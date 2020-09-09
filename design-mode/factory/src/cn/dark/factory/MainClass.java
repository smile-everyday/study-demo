package cn.dark.factory;

/**
 * @author dark
 * @date 2018-10-03
 */
public class MainClass {

    public static void main(String[] args) {
        Factory factory = new BikeFactory();
        Vehicle benz = factory.getCar();
        factory = new CarFactory();
        Vehicle bmw = factory.getCar();
    }

}

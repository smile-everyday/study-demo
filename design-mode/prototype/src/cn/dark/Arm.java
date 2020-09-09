package cn.dark;

/**
 * @author dark
 * @date 2019-02-09
 */
public class Arm implements Cloneable {

    double length = 5;

    @Override
    public Arm clone() throws CloneNotSupportedException {
        return (Arm) super.clone();
    }
}

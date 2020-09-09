package cn.dark;

/**
 * @author dark
 * @date 2019-02-09
 */
public class Head implements Cloneable {

    double weight = 10;

    @Override
    public Head clone() throws CloneNotSupportedException {
        return (Head) super.clone();
    }
}

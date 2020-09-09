package cn.dark.element;

import cn.dark.visitor.IVisitor;

/**
 * @author dark
 * @date 2019-05-11
 */
public abstract class Components {

    protected String brand;

    public Components(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public abstract void accept(IVisitor visitor);

}

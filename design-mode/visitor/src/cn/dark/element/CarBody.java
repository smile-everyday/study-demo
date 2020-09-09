package cn.dark.element;

import cn.dark.visitor.IVisitor;

/**
 * 车身
 *
 * @author dark
 * @date 2019-05-11
 */
public class CarBody extends Components {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarBody(String brand, String color) {
        super(brand);
        this.color = color;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

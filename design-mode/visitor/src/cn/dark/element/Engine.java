package cn.dark.element;

import cn.dark.visitor.IVisitor;

/**
 * 引擎
 *
 * @author dark
 * @date 2019-05-11
 */
public class Engine extends Components {

    private String power;

    public Engine(String brand, String power) {
        super(brand);
        this.power = power;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}

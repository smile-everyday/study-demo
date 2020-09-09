package cn.dark.visitor;

import cn.dark.element.CarBody;
import cn.dark.element.Engine;

/**
 * 奔驰改装者访问者
 *
 * @author dark
 * @date 2019-05-11
 */
public class BenzModifiedVisitor implements IVisitor {

    @Override
    public void visit(CarBody carBody) {
        System.out.println("改装前品牌：" + carBody.getBrand() + "，改装前车身颜色：" + carBody.getColor());
        carBody.setBrand("奔驰");
        carBody.setColor("blue");
        System.out.println("改装后品牌：" + carBody.getBrand() + "，改装后车身颜色：" + carBody.getColor());
    }

    @Override
    public void visit(Engine engine) {
        System.out.println("改装前品牌：" + engine.getBrand() + "，改装前功率：" + engine.getPower());
        engine.setPower("300");
        engine.setBrand("奔驰");
        System.out.println("改装后品牌：" + engine.getBrand() + "，改装后功率：" + engine.getPower());
    }

}

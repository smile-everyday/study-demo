package cn.dark.visitor;

import cn.dark.element.CarBody;
import cn.dark.element.Engine;

/**
 * 访问者接口
 *
 * @author dark
 * @date 2019-05-11
 */
public interface IVisitor {

    void visit(CarBody carBody);

    void visit(Engine engine);

}

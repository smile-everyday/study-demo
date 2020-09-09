package cn.dark;

import cn.dark.element.CarBody;
import cn.dark.element.Components;
import cn.dark.element.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * 元素容器，提供element类的集合
 *
 * @author dark
 * @date 2019-05-11
 */
public class ObjectStructure {

    public static List<Components> getComponents() {
        String brand = "大众";
        CarBody carBody = new CarBody(brand, "红色");
        Engine engine = new Engine(brand, "100");

        List<Components> list = new ArrayList<>();
        list.add(carBody);
        list.add(engine);
        return list;
    }
}

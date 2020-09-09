package cn.dark;

import cn.dark.element.Components;
import cn.dark.visitor.BenzModifiedVisitor;
import cn.dark.visitor.BugattiModifiedVisitor;
import cn.dark.visitor.IVisitor;

import java.util.List;

/**
 * @author dark
 * @date 2019-05-11
 */
public class MainClass {

    public static void main(String[] args) {
        IVisitor benzModifiedVisitor = new BenzModifiedVisitor();
        BugattiModifiedVisitor bugattiModifiedVisitor = new BugattiModifiedVisitor();

        List<Components> components = ObjectStructure.getComponents();
        for (Components component : components) {
            component.accept(benzModifiedVisitor);
            component.accept(bugattiModifiedVisitor);
        }
    }

}

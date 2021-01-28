package cn.dark.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lwj
 * @date 2021-01-21
 */
public class PECSDemo {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();

        // extend无法添加元素，集合本身只能是B及B子类的集合，所以集合中元素可能是B的任意子类（C或D）
        // List<? extends B> aList = new ArrayList<A>();
        List<? extends B> bList = new ArrayList<B>();
        // bList.add(a);
        // bList.add(b);
        // bList.add(c);
        B b1 = bList.get(0);
        List<? extends B> cList = new ArrayList<C>();
        B b2 = cList.get(0);
        List<? extends B> dList = new ArrayList<D>();
        B b3 = dList.get(0);

        // super集合中元素都是B的子类，但集合本身只能是B及B父类的集合，所以获取元素只能用Object接收，只能添加Object的子类
        List<? super B> aList1 = new ArrayList<A>();
        Object object = aList1.get(0);
        // aList1.add(a);
        aList1.add(b);
        aList1.add(c);
        aList1.add(d);
        List<? super B> bList1 = new ArrayList<B>();
        // List<? super B> cList1 = new ArrayList<C>();
        // List<? super B> dList1 = new ArrayList<D>();

    }

}

class A {

}

class B extends A {

}

class C extends B {

}

class D extends B {

}
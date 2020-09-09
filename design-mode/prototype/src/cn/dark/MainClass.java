package cn.dark;

/**
 * @author dark
 * @date 2019-02-09
 */
public class MainClass {

    public static void main(String[] args) throws CloneNotSupportedException {
        People people = new People();
        People clone = people.clone();

        // System.out.println(people == clone);
        // System.out.println(people.name == clone.name);
        // System.out.println(people.age == clone.age);
        // System.out.println(people.head == clone.head);
        // System.out.println(people.arms == clone.arms);

        System.out.println("-------name-------");
        people.name = "Harry";
        System.out.println(people.name);
        System.out.println(clone.name);

        System.out.println("--------age------");
        people.age = 555;
        System.out.println(people.age);
        System.out.println(clone.age);

        System.out.println("-------head-------");
        System.out.println("改变前people：" + people.head.weight);
        System.out.println("改变前clone：" + clone.head.weight);
        people.head.weight = 55;
        System.out.println("改变后people：" + people.head.weight);
        System.out.println("改变后clone：" + clone.head.weight);

        System.out.println("-------arm-------");
        // 先检验数组中对象
        System.out.println("改变前people：" + people.arms[0]);
        System.out.println("改变前clone：" + clone.arms[0]);
        people.arms[0] = null;
        System.out.println("改变后people：" + people.arms[0]);
        System.out.println("改变后clone：" + clone.arms[0]);
    }

}

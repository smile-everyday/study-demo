package cn.dark;

/**
 * @author dark
 * @date 2019-02-09
 */
public class People implements Cloneable {

    public String name;
    public Integer age;
    public Head head;
    public Arm[] arms;

    public People() {
        this.name = "Jack";
        this.age = 1111;
        this.head = new Head();

        Arm left = new Arm();
        Arm right = new Arm();
        arms = new Arm[2];
        arms[0] = left;
        arms[1] = right;
        System.out.println("111");
    }

    /**
     * 重写clone方法，需要注意的是Object中
     */
    @Override
    public People clone() throws CloneNotSupportedException {
        People clone = (People) super.clone();
        clone.head = head.clone();
        clone.arms = arms.clone();
        return clone;
    }
}

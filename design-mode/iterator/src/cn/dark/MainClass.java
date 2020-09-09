package cn.dark;

/**
 * @author dark
 * @date 2019-02-07
 */
public class MainClass {

    public static void main(String[] args) {
        Takeaway takeaway = new Takeaway(new A(), new B());
        takeaway.print();
    }

}

package cn.dark;

/**
 * @author dark
 * @date 2018-12-08
 */
public class XiaoMing {

    public static void main(String[] args) {
        Andriod andriod = new Andriod();
        IPhone iPhone = new AndriodAdatper(andriod);
        iPhone.iPhoneCharging();
    }

}

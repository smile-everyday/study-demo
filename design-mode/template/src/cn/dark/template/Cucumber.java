package cn.dark.template;

import java.util.Scanner;

/**
 * @author dark
 * @date 2018-10-20
 */
public class Cucumber extends Vegetables {

    @Override
    protected void cooking() {
        System.out.println("凉拌黄瓜！");
    }

    @Override
    protected boolean isPeel() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        if ("n".equals(str)) {
            return false;
        }
        return true;
    }
}

package cn.dark;

import cn.dark.abstraction.BigWrittingBrush;
import cn.dark.abstraction.MediumWrittingBrush;
import cn.dark.abstraction.SmallWrittingBrush;
import cn.dark.abstraction.WrittingBrush;
import cn.dark.implementor.Black;
import cn.dark.implementor.Color;
import cn.dark.implementor.Green;
import cn.dark.implementor.Red;

/**
 * @author dark
 * @date 2019-02-10
 */
public class MainClass {

    public static void main(String[] args) {
        Color red = new Red();
        Color green = new Green();
        Color black = new Black();

        WrittingBrush brush = new BigWrittingBrush();
        brush.setColor(red);
        brush.setColor(green);
        brush.setColor(black);

        brush = new MediumWrittingBrush();
        brush.setColor(red);
        brush.setColor(green);
        brush.setColor(black);
    }

}

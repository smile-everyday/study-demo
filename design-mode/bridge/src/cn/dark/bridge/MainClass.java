package cn.dark.bridge;

import cn.dark.bridge.abstraction.BigWrittingBrush;
import cn.dark.bridge.abstraction.MediumWrittingBrush;
import cn.dark.bridge.abstraction.SmallWrittingBrush;
import cn.dark.bridge.abstraction.WrittingBrush;
import cn.dark.bridge.implementor.Black;
import cn.dark.bridge.implementor.Color;
import cn.dark.bridge.implementor.Green;
import cn.dark.bridge.implementor.Red;

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

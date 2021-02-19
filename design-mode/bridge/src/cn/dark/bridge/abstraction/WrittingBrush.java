package cn.dark.bridge.abstraction;

import cn.dark.bridge.implementor.Color;

/**
 * @author dark
 * @date 2019-02-10
 */
public abstract class WrittingBrush {

    protected String size;
    protected Color color;

    /**
     * 通过该方法为毛笔着色
     */
    public void setColor(Color color) {
        this.color = color;
        System.out.println(size + "毛笔开始着色：" + color.getHue());

    }
}

package cn.dark.elementary.command;

import cn.dark.elementary.receiver.Light;

/**
 * @author dark
 * @date 2018-12-01
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void excute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

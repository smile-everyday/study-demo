package cn.dark.elementary.command;

import cn.dark.elementary.receiver.Light;

/**
 * @author dark
 * @date 2018-12-01
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void excute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

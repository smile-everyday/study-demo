package cn.dark.elementary.command;

import cn.dark.elementary.receiver.TV;

/**
 * @author dark
 * @date 2018-12-01
 */
public class TVOffCommand implements Command {

    private TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void excute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}

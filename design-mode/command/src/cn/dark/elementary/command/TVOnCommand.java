package cn.dark.elementary.command;

import cn.dark.elementary.receiver.TV;

/**
 * @author dark
 * @date 2018-12-01
 */
public class TVOnCommand implements Command {

    private TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void excute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}

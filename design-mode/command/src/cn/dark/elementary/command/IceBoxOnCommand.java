package cn.dark.elementary.command;

import cn.dark.elementary.receiver.IceBox;

/**
 * @author dark
 * @date 2018-12-01
 */
public class IceBoxOnCommand implements Command {
    private IceBox iceBox;

    public IceBoxOnCommand(IceBox iceBox) {
        this.iceBox = iceBox;
    }

    @Override
    public void excute() {
        iceBox.on();
    }

    @Override
    public void undo() {
        iceBox.off();
    }
}

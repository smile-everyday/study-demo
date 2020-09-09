package cn.dark.elementary.command;

import cn.dark.elementary.receiver.IceBox;

/**
 * @author dark
 * @date 2018-12-01
 */
public class IceBoxOffCommand implements Command {

    private IceBox iceBox;

    public IceBoxOffCommand(IceBox iceBox) {
        this.iceBox = iceBox;
    }

    @Override
    public void excute() {
        iceBox.off();
    }

    @Override
    public void undo() {
        iceBox.on();
    }
}

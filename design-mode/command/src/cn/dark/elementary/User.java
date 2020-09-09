package cn.dark.elementary;

import cn.dark.elementary.command.*;
import cn.dark.elementary.receiver.IceBox;
import cn.dark.elementary.receiver.Light;

/**
 * @author dark
 * @date 2018-12-01
 */
public class User {

    public static void main(String[] args) {
        Light light = new Light();
        Command onCommand = new LightOnCommand(light);
        Command offCommand = new LightOffCommand(light);
        RemoteController remote = new RemoteController();
        remote.setCommands(0, onCommand, offCommand);

        IceBox iceBox = new IceBox();
        onCommand = new IceBoxOnCommand(iceBox);
        offCommand = new IceBoxOffCommand(iceBox);
        remote.setCommands(1, onCommand, offCommand);

        remote.pressOnButton(0);
        remote.pressOnButton(1);
        remote.pressUndo();
    }

}

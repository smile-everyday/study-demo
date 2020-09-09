package cn.dark.elementary;

import cn.dark.elementary.command.Command;
import cn.dark.elementary.command.NoCommand;

/**
 * @author dark
 * @date 2018-12-01
 */
public class RemoteController {

    private Command[] onCommands;
    private Command[] offCommands;

    private Command preCommand;

    public RemoteController() {
        this.onCommands = new Command[10];
        this.offCommands = new Command[10];

        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < onCommands.length; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommands(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pressOnButton(int slot) {
        onCommands[slot].excute();
        preCommand = onCommands[slot];
    }

    public void pressOffButton(int slot) {
        offCommands[slot].excute();
        preCommand = offCommands[slot];
    }

    public void pressUndo() {
        preCommand.undo();
    }
    
}

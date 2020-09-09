package cn.dark.elementary.command;

/**
 * @author dark
 * @date 2018-12-01
 */
public class PartyOnCommand implements Command {
    private Command[] commands;

    public PartyOnCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void excute() {
        for (Command command : commands) {
            command.excute();
        }
    }

    @Override
    public void undo() {
        for (Command command : commands) {
            command.undo();
        }
    }
}

package commands;

import commands.enums.Direction;

public class MoveCommand implements Command {
    private Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String getCommandCode() {
        return CommandUtils.getDirection(direction);
    }
}

package commands;

import commands.enums.Direction;

public class TakeResourceCommand implements Command {
    private Direction direction;

    public TakeResourceCommand(Direction direction) {
        this.direction = direction;
    }


    @Override
    public String getCommandCode() {
        return "tr" + CommandUtils.getDirection(direction);
    }
}

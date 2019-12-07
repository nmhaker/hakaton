package commands;

import commands.enums.Direction;

public class TakeWeaponCommand implements Command {
    private Direction direction;

    public TakeWeaponCommand(Direction direction) {
        this.direction = direction;
    }


    @Override
    public String getCommandCode() {
        return "tw" + CommandUtils.getDirection(direction);
    }
}

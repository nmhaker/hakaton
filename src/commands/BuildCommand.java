package commands;

import commands.enums.Building;
import commands.enums.Direction;

public class BuildCommand implements Command{

    private Direction direction;
    private Building building;

    public BuildCommand(Direction direction, Building building) {
        this.direction = direction;
        this.building = building;
    }

    @Override
    public String getCommandCode() {
        return "b" + CommandUtils.getBuilding(building) + CommandUtils.getDirection(direction);
    }
}

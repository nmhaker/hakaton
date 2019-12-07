package commands;

import commands.enums.Direction;
import commands.enums.Weapon;

public class AttackCommand implements Command {

    private Direction direction;
    private Weapon weapon;

    public AttackCommand(Direction direction, Weapon weapon) {
        this.direction = direction;
        this.weapon = weapon;
    }


    @Override
    public String getCommandCode() {
        return CommandUtils.getAttackWeapon(weapon) + "a" + CommandUtils.getAttackDirection(direction);
    }
}

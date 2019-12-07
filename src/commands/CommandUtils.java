package commands;

import commands.enums.Building;
import commands.enums.Direction;
import commands.enums.Resource;
import commands.enums.Weapon;

public class CommandUtils {

    public static String getDirection(Direction direction) {
        switch(direction) {
            case DOWN: return "s";
            case LEFT: return "a";
            case RIGHT: return "d";
            case UP: return "w";
            default: return "";
        }
    }

    public static String getAttackWeapon(Weapon weapon) {
        switch(weapon) {
            case ARROW: return "a";
            case SWORD: return "s";
            default: return "";
        }
    }

    public static String getAttackDirection(Direction direction) {
        switch(direction) {
            case DOWN:
            case LEFT:
            case RIGHT:
            case UP: return getDirection(direction);
            case UPPER_LEFT: return "wa";
            case UPPER_RIGHT: return "wd";
            case LOWER_LEFT: return "sa";
            case LOWER_RIGHT: return "sd";
            default: return "";
        }
    }

    public static String getResource(Resource resource) {

        switch(resource){
            case WOOD: return "w";
            case STONE: return "s";
            case METAL: return "m";
            default: return "";
        }
    }

    public static String getBuilding(Building building) {
        switch(building) {
            case HOUSE: return "h";
            case FORTRESS: return "f";
            case ARROW_FORTRESS: return "af";
            case SHIELD_FORTRESS: return "shf";
            case SWORD_FORTRESS: return "sf";
            default: return "";
        }
    }
}

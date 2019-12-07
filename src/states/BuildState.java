package states;

import bot.Helpers;
import bot.Bot;
import bot.PathHelper;
import commands.BuildCommand;
import commands.Command;
import commands.TakeResourceCommand;
import commands.TakeWeaponCommand;
import commands.enums.Building;
import commands.enums.Direction;
import commands.enums.Weapon;
import constants.ItemType;
import models.Tile;

public class BuildState extends State {

//    private int remainingWood;
//    private int remainingStone;
//    private int remainingMetal;
//    private int nextBuilding;
    enum BuildSubStates {
        GET3_STONES_INITIAL,
        GET_3_STONES,
        MOVE_TO_HOUSE,
        MOVE_TO_SWORD_FORTRESS,
        GET_3_METAL_INITIAL,
        GET_3_METAL,
        GET_2_WOOD_INITIAL,
        GET_2_WOOD,
        MOVE_TO_FORTRESS,
        BUILD_FORTRESS_SWORD,
        GET_4_STONE_INITIAL,
        GET_4_STONE,
        BUILD_HOUSE,
        DROP_1_WOOD
    }

    BuildSubStates buildSubState = BuildSubStates.GET3_STONES_INITIAL;
    private Tile woodShop;
    private Tile stoneShop;
    private Tile metalShop;

    private static BuildState build_state = null;
    private BuildState(Bot bot) { this.bot = bot; }

    public static BuildState getInstance(Bot bot)
    {
        if (build_state == null)
            build_state = new BuildState(bot);

        return build_state;
    }
    @Override
    public String chooseAction() {
        switch(buildSubState) {
            case GET3_STONES_INITIAL:
                stoneShop = Helpers.GetNearestItem(ItemType.STONE_SHOP);
                buildSubState = BuildSubStates.GET_3_STONES;
            case GET_3_STONES:
                if(!Helpers.nextToItem(ItemType.STONE_SHOP)) {
                    Command dd = PathHelper.getNextMove(stoneShop.item.x, stoneShop.item.y);
                    return dd.getCommandCode();
                } else {
                    Direction d = Helpers.directionTo(ItemType.STONE_SHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.GetNumberOfStone() == 2) {
                        buildSubState = BuildSubStates.MOVE_TO_HOUSE;
                    }
                    return ret.getCommandCode();
                }
            case MOVE_TO_HOUSE:
                Tile house = Helpers.GetNearestItem(ItemType.HOUSE);
                if(!Helpers.nextToItem(ItemType.HOUSE)) {
                    Command houseD = PathHelper.getNextMove(house.item.x, house.item.y );
                    return houseD.getCommandCode();
                } else {
                    Direction fortD = Helpers.directionTo(ItemType.HOUSE);
                    Command fortRet = new BuildCommand(fortD, Building.FORTRESS);
                    buildSubState = BuildSubStates.MOVE_TO_SWORD_FORTRESS;
                    return fortRet.getCommandCode();
                }
            case MOVE_TO_SWORD_FORTRESS:
                Tile swFortress = Helpers.GetNearestItem(ItemType.SWORD_FORTRESS);
                if(!Helpers.nextToItem(ItemType.SWORD_FORTRESS)) {
                    Command houseD = PathHelper.getNextMove(swFortress.item.x, swFortress.item.y );
                    return houseD.getCommandCode();
                } else {
                    Direction fortD = Helpers.directionTo(ItemType.SWORD_FORTRESS);
                    Command fortRet = new TakeWeaponCommand(fortD);
                    buildSubState = BuildSubStates.GET_3_METAL_INITIAL;
                    return fortRet.getCommandCode();
                }
            case GET_3_METAL_INITIAL:
                metalShop = Helpers.GetNearestItem(ItemType.METAL_SHOP);
                buildSubState = BuildSubStates.GET_3_METAL;
            case GET_3_METAL:
                if(!Helpers.nextToItem(ItemType.METAL_SHOP)) {
                    Command dd = PathHelper.getNextMove(metalShop.item.x, metalShop.item.y);
                    return dd.getCommandCode();
                } else {
                    Direction d = Helpers.directionTo(ItemType.METAL_SHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.GetNumberOfMetal() == 2) {
                        buildSubState = BuildSubStates.GET_2_WOOD_INITIAL;
                    }
                    return ret.getCommandCode();
                }
            case GET_2_WOOD_INITIAL:
                woodShop = Helpers.GetNearestItem(ItemType.WOOD_SHOP);
                buildSubState = BuildSubStates.GET_2_WOOD;
            case GET_2_WOOD:
                if(!Helpers.nextToItem(ItemType.WOOD_SHOP)) {
                    Command dd = PathHelper.getNextMove(woodShop.item.x, woodShop.item.y);
                    return dd.getCommandCode();
                } else {
                    Direction d = Helpers.directionTo(ItemType.METAL_SHOP);
                    Command ret = new TakeResourceCommand(d);
                    if (Helpers.GetNumberOfWood() == 1) {
                        buildSubState = BuildSubStates.MOVE_TO_FORTRESS;
                    }
                    return ret.getCommandCode();
                }
            case MOVE_TO_FORTRESS:
                Tile fortress1 = Helpers.GetNearestItem(ItemType.FORTRESS);
                if(!Helpers.nextToItem(ItemType.FORTRESS)) {
                    Command houseD1 = PathHelper.getNextMove(fortress1.item.x, fortress1.item.y );
                    return houseD1.getCommandCode();
                } else {
                    Direction fortD1 = Helpers.directionTo(ItemType.FORTRESS);
                    Command fortRet = new TakeWeaponCommand(fortD1);
                    buildSubState = BuildSubStates.GET_3_METAL_INITIAL;
                    return fortRet.getCommandCode();
                }
        }
        return null;
    }
}

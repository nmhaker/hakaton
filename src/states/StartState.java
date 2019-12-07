package states;

import bot.Helpers;
import bot.PathHelper;
import commands.BuildCommand;
import commands.Command;
import commands.MoveCommand;
import commands.TakeResourceCommand;
import commands.enums.Building;
import commands.enums.Direction;
import constants.ItemType;
import models.Tile;
import models.items.Item;

import java.nio.file.Path;

public class StartState extends State {
    enum StartSubState {
        GETWOOD,
        GETSTONE,
        BUILDHOUSE,
        TAKE3STONE,
        BUILDFORTRESS,
        TAKE1STONE,
        GETMETAL,
        BUILDSWORD,
        GET4WOOD,
        BUILDNEWHOUSE
    }

    StartSubState startSubState = StartSubState.GETWOOD;

    private static StartState start_state = null;
    private StartState() {}

    public static StartState getInstance()
    {
        if (start_state == null)
            start_state = new StartState();

        return start_state;
    }

    @Override
    public String chooseAction() {
        switch(startSubState) {
            case GETWOOD:

                Tile woodShop = Helpers.GetNearestItem(ItemType.WOOD_SHOP);
                if(!Helpers.nextToItem(ItemType.WOOD_SHOP)) {
                    Command dd = PathHelper.getNextMove(woodShop.item.x, woodShop.item.y);
                    return dd.getCommandCode();
                } else {
                    Direction d = Helpers.directionTo(ItemType.WOOD_SHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.GetNumberOfWood() == 3) {
                        startSubState = StartSubState.GETSTONE;
                    }
                    return ret.getCommandCode();
                }
            case GETSTONE:

                Tile stoneShop = Helpers.GetNearestItem(ItemType.STONE_SHOP);
                if(!Helpers.nextToItem(ItemType.STONE_SHOP)) {
                    Command d = PathHelper.getNextMove(stoneShop.item.x, stoneShop.item.y);
                    return d.getCommandCode();
                } else {
                    Direction d = Helpers.directionTo(ItemType.STONE_SHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.GetNumberOfStone() == 3) {
                        startSubState = StartSubState.BUILDHOUSE;
                    }
                    return ret.getCommandCode();
                }
            case BUILDHOUSE: //TODO chech if you are blocked
                    Direction freeD = Helpers.GetFreeTileDirection();
                    startSubState = StartSubState.TAKE3STONE;
                    return new BuildCommand(freeD, Building.HOUSE).getCommandCode();
            case TAKE3STONE:
                    Direction d = Helpers.directionTo(ItemType.STONE_SHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.GetNumberOfStone() == 2) {
                        startSubState = StartSubState.BUILDFORTRESS;
                    }
                    return ret.getCommandCode();
            case BUILDFORTRESS:
                    Direction buildDi = Helpers.directionTo(ItemType.HOUSE);
                    startSubState = StartSubState.TAKE1STONE;
                    return new BuildCommand(buildDi, Building.FORTRESS).getCommandCode();
            case TAKE1STONE:
                    Direction dirc = Helpers.directionTo(ItemType.STONE_SHOP);
                    startSubState = StartSubState.GETMETAL;
                    return new TakeResourceCommand(dirc).getCommandCode();
            case GETMETAL:
                Tile metalShop = Helpers.GetNearestItem(ItemType.METAL_SHOP);
                if(!Helpers.nextToItem(ItemType.METAL_SHOP)) {
                    Command metalDir = PathHelper.getNextMove(metalShop.item.x, metalShop.item.y);
                    return metalDir.getCommandCode();
                } else {
                    Direction metD = Helpers.directionTo(ItemType.METAL_SHOP);
                    Command retCo = new TakeResourceCommand(metD);
                    if(Helpers.GetNumberOfMetal() == 2) {
                        startSubState = StartSubState.BUILDSWORD;
                    }
                    return retCo.getCommandCode();
                }
            case BUILDSWORD:
                Tile fortress = Helpers.GetNearestItem(ItemType.FORTRESS);
                if(!Helpers.nextToItem(ItemType.FORTRESS)) {
                    Command fortressD = PathHelper.getNextMove(fortress.item.x, fortress.item.y );
                    return fortressD.getCommandCode();
                } else {
                    Direction fortD = Helpers.directionTo(ItemType.FORTRESS);
                    Command fortRet = new BuildCommand(fortD, Building.SWORD_FORTRESS);
                    return fortRet.getCommandCode();
                }
            case GET4WOOD:
                Tile woodShop1 = Helpers.GetNearestItem(ItemType.WOOD_SHOP);
                if(!Helpers.nextToItem(ItemType.WOOD_SHOP)) {
                    Command woodD1 = PathHelper.getNextMove(woodShop1.item.x, woodShop1.item.y);
                    return woodD1.getCommandCode();
                } else {
                    Direction woodDir2 = Helpers.directionTo(ItemType.WOOD_SHOP);
                    Command woodDirRet2 = new TakeResourceCommand(woodDir2);
                    if(Helpers.GetNumberOfWood() == 3) {
                        startSubState = StartSubState.BUILDNEWHOUSE;
                    }
                    return woodDirRet2.getCommandCode();
                }
            case BUILDNEWHOUSE:
                // TODO:
                Direction tmp = Helpers.GetFreeTileDirection();
                return new MoveCommand(tmp).getCommandCode();
        }
        return null;
    }
}

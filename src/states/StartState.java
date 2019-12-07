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
        GETWOODINITIAL,
        GETWOOD,
        GETSTONEINITIAL,
        GETSTONE,
        BUILDHOUSE,
        TAKE3STONE,
        BUILDFORTRESS,
        TAKE1STONE,
        GETMETALINITIAL,
        GETMETAL,
        GET1WOODINITIAL,
        GET1WOOD,
        BUILDSWORD,
        GET4WOODINITIAL,
        GET4WOOD,
        BUILDNEWHOUSE,
        LOOPSEAMLESS
    }

    StartSubState startSubState = StartSubState.GETWOODINITIAL;

    private static StartState start_state = null;
    private Tile woodShop;
    private Tile stoneShop;
    private Tile metalShop;
    private Tile woodShop1;

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
            case GETWOODINITIAL:
                 woodShop = Helpers.GetNearestItem(ItemType.WOOD_SHOP);
                 startSubState = StartSubState.GETWOOD;
            case GETWOOD:
                if(!Helpers.nextToItem(ItemType.WOOD_SHOP)) {
                    Command dd = PathHelper.getNextMove(woodShop.item.x, woodShop.item.y);
                    return dd.getCommandCode();
                } else {
                    Direction d = Helpers.directionTo(ItemType.WOOD_SHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.GetNumberOfWood() == 3) {
                        startSubState = StartSubState.GETSTONEINITIAL;
                    }
                    return ret.getCommandCode();
                }

            case GETSTONEINITIAL:
                stoneShop = Helpers.GetNearestItem(ItemType.STONE_SHOP);
                startSubState = StartSubState.GETSTONE;
            case GETSTONE:
                if(!Helpers.nextToItem(ItemType.STONE_SHOP)) {
                    Command d = PathHelper.getNextMove(stoneShop.item.x, stoneShop.item.y);
                    return d.getCommandCode();
                } else {
                    Direction d = Helpers.directionTo(ItemType.STONE_SHOP);
                    Command ret = new TakeResourceCommand(d);
                    startSubState = StartSubState.BUILDHOUSE;
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
                    startSubState = StartSubState.GETMETALINITIAL;
                    return new TakeResourceCommand(dirc).getCommandCode();
            case GETMETALINITIAL:
                metalShop = Helpers.GetNearestItem(ItemType.METAL_SHOP);
                startSubState = StartSubState.GETMETAL;
            case GETMETAL:
                if(!Helpers.nextToItem(ItemType.METAL_SHOP)) {
                    Command metalDir = PathHelper.getNextMove(metalShop.item.x, metalShop.item.y);
                    return metalDir.getCommandCode();
                } else {
                    Direction metD = Helpers.directionTo(ItemType.METAL_SHOP);
                    Command retCo = new TakeResourceCommand(metD);
                    if(Helpers.GetNumberOfMetal() == 2) {
                        startSubState = StartSubState.GET1WOODINITIAL;
                    }
                    return retCo.getCommandCode();
                }
            case GET1WOODINITIAL:
                woodShop = Helpers.GetNearestItem(ItemType.WOOD_SHOP);
                startSubState = StartSubState.GET1WOOD;
            case GET1WOOD:
                if(!Helpers.nextToItem(ItemType.WOOD_SHOP)) {
                    Command dd = PathHelper.getNextMove(woodShop.item.x, woodShop.item.y);
                    return dd.getCommandCode();
                } else {
                    Direction d1 = Helpers.directionTo(ItemType.WOOD_SHOP);
                    Command ret1 = new TakeResourceCommand(d1);
                    startSubState = StartSubState.BUILDSWORD;
                    return ret1.getCommandCode();
                }
            case BUILDSWORD:
                Tile fortress = Helpers.GetNearestItem(ItemType.FORTRESS);
                if(!Helpers.nextToItem(ItemType.FORTRESS)) {
                    Command fortressD = PathHelper.getNextMove(fortress.item.x, fortress.item.y );
                    return fortressD.getCommandCode();
                } else {
                    Direction fortD = Helpers.directionTo(ItemType.FORTRESS);
                    Command fortRet = new BuildCommand(fortD, Building.SWORD_FORTRESS);
                    startSubState = StartSubState.GET4WOODINITIAL;
                    return fortRet.getCommandCode();
                }
            case GET4WOODINITIAL:
                woodShop1 = Helpers.GetNearestItem(ItemType.WOOD_SHOP);
                startSubState = StartSubState.GET4WOOD;
            case GET4WOOD:
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
                Direction freeD2 = Helpers.GetFreeTileDirection();
                startSubState = StartSubState.LOOPSEAMLESS;
                return new BuildCommand(freeD2, Building.HOUSE).getCommandCode();
            case LOOPSEAMLESS:
                Direction tmp = Helpers.GetFreeTileDirection();
                return new MoveCommand(tmp).getCommandCode();
        }
        return null;
    }
}

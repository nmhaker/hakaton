package states;

import bot.Helpers;

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
                /*
                WoodShop woodShop = Helpers.getClosestWoodShop();
                if(!Helpers.nextTo(ItemType.WOODSHOP)) {
                    Direction d = Helpers.getNextStep(...);
                    return new MoveCommand(d);
                } else {
                    Direction d = DirectionTo(ItemType.WOODSHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.numberOfWood == 3) {
                        startSubState = GETSTONE;
                    }
                    return ret;
                }
                */
                break;
            case GETSTONE:
                /*
                StoneShop stoneShop = Helpers.getClosestStoneShop();
                if(!Helpers.nextTo(ItemType.STONESHOP)) {
                    Direction d = Helpers.getNextStep(...);
                    return new MoveCommand(d);
                } else {
                    Direction d = DirectionTo(ItemType.STONESHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.numberOfWood == 3) {
                        startSubState = BUILDHOUSE;
                    }
                    return ret;
                }
                */
                break;
            case BUILDHOUSE:
                /* if(Helpers.anyFreeSpotExists()){
                    Direction d = Hellpers.getFreeSpot();
                    startSubState = TAKE3STONE;
                    return new BuildCommand(d);
                } else {
                    // move somewhere
                }
                */
                break;
            case TAKE3STONE:
                /*
                if() {
                    Direction d = DirectionTo(ItemType.STONESHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.numberOfStone == 2) {
                        startSubState = BUILDFORTRESS;
                    }
                    return ret;
                } else {
                    vrati se do STONESHOP;
                }
                */
                break;
            case BUILDFORTRESS:
                /*
                    Direction d = Hellpers.getMyHouse();
                    startSubState = TAKE1STONE;
                    return new BuildCommand(d);

                */
                break;
            case TAKE1STONE:
                /*
                    Direction d = Hellpers.DirectTo();
                    startSubState = GETMETAL;
                    return new TakeResourceCommand(d);
                */
                break;
            case GETMETAL:
                /*
                MetalShop metalShop = Helpers.getClosestMetalShop();
                if(!Helpers.nextTo(ItemType.METALSHOP)) {
                    Direction d = Helpers.getNextStep(...);
                    return new MoveCommand(d);
                } else {
                    Direction d = DirectionTo(ItemType.METALSHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.numberOfMetal == 2) {
                        startSubState = BUILDSWORD;
                    }
                    return ret;
                }
                */
                break;
            case BUILDSWORD:
                /*
                Fortress fortress = Helpers.getMyFortress();
                if(!Helpers.nextTo(ItemType.FORTRESS)) {
                    Direction d = Helpers.getNextStep(...);
                    return new MoveCommand(d);
                } else {
                    Direction d = DirectionTo(ItemType.FORTRESS);
                    Command ret = new BuildCommand(d);
                    return ret;
                }
                */
                break;
            case GET4WOOD:
                /*
                WoodShop woodShop = Helpers.getClosestWoodShop();
                if(!Helpers.nextTo(ItemType.WOODSHOP)) {
                    Direction d = Helpers.getNextStep(...);
                    return new MoveCommand(d);
                } else {
                    Direction d = DirectionTo(ItemType.WOODSHOP);
                    Command ret = new TakeResourceCommand(d);
                    if(Helpers.numberOfWood == 3) {
                        startSubState = BUILDNEWHOUSE;
                    }
                    return ret;
                }
                */
                break;
            case BUILDNEWHOUSE:
                // TODO:
                break;
        }
        return null;
    }
}

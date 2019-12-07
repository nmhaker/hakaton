package states;

import bot.Bot;
import bot.Helpers;
import bot.PathHelper;
import commands.Command;
import commands.MoveCommand;
import commands.TakeWeaponCommand;
import commands.enums.Direction;
import constants.ItemType;
import models.Tile;
import models.items.Item;
import models.items.fortresses.ShieldFortress;
import models.items.fortresses.SwordFortress;

public class RetreatState extends State {
    enum RetreatSubState {
        RETREATINITIAL,
        RETREATTAKESWORD,
        GOTOBUILDSTATE,
        GOTOATACKSTATE


    }
    RetreatSubState retreatSubState = RetreatState.RetreatSubState.RETREATINITIAL;
    private static RetreatState retreat_state = null;
    private RetreatState(Bot bot) { this.bot = bot;}
    private Tile swordFortress;
    public static RetreatState getInstance(Bot bot)
    {
        if (retreat_state == null)
            retreat_state = new RetreatState(bot);

        return retreat_state;
    }

    @Override
    public String chooseAction() {
        switch(retreatSubState){
            case RETREATINITIAL:
                 swordFortress = Helpers.GetNearestItem(ItemType.SWORD_FORTRESS);
                 if(swordFortress == null) {
                     retreatSubState = RetreatSubState.GOTOBUILDSTATE;
                 } else{
                     retreatSubState = RetreatSubState.RETREATINITIAL;
                 }
            case RETREATTAKESWORD:  // next nesto
                if( swordFortress != null && ((SwordFortress)swordFortress.item).numWeapons > 0 && Helpers.GenNumOfWeapons() != 2){
                    if(!Helpers.nextToItem(ItemType.SWORD_FORTRESS)) {
                        Command houseD = PathHelper.getNextMove(swordFortress.item.x, swordFortress.item.y );
                        return houseD.getCommandCode();
                    } else {
                        Direction fortD = Helpers.directionTo(ItemType.SWORD_FORTRESS);
                        Command fortRet = new TakeWeaponCommand(fortD);
                        retreatSubState = retreatSubState.GOTOATACKSTATE;
                        return fortRet.getCommandCode();
                    }
                } else{
                    retreatSubState = retreatSubState.GOTOBUILDSTATE;
                    Direction dd = Helpers.GetFreeTileDirection();
                    return new MoveCommand(dd).getCommandCode();
                }
            case GOTOBUILDSTATE:
                bot.changeState(StateEnum.BUILD);
                return BuildState.getInstance(bot).chooseAction();
            case GOTOATACKSTATE:
                bot.changeState(StateEnum.ATTACK);
                return AttackState.getInstance(bot).chooseAction();
        }
        return null;
    }
}

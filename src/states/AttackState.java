package states;

import bot.Helpers;
import bot.PathHelper;
import commands.AttackCommand;
import commands.Command;
import commands.enums.Direction;
import commands.enums.Weapon;
import constants.WeaponType;
import models.Player;

public class AttackState extends State {
    enum AttackSubState {
        ATTACK_PLAYER,
        ATTACK_BUILDING
    }

    private static AttackState attack_state = null;
    private AttackSubState attackSubState = AttackSubState.ATTACK_BUILDING;
    private AttackState() {}


    public static AttackState getInstance()
    {
        if (attack_state == null)
            attack_state = new AttackState();

        return attack_state;
    }

    @Override
    public String chooseAction() {

        Command command = null;



        switch (attackSubState) {
            case ATTACK_PLAYER:
                Player me = Helpers.returnMe();
                Player enemy = Helpers.returnEnemy();
//                if ((me.weapon1 != null && WeaponType.ARROW == me.weapon1.type ||
//                    me.weapon2 != null && WeaponType.ARROW == me.weapon2.type) &&
//                    Helpers.DistanceToEnemy() <= 3 &&
//                    Helpers.EnemyReachableWithArrows()) {
//                    //TODO: Try attack with arrow
//                }

                if (Helpers.EnemyReachableWithSword()) {
                    if (me.x == enemy.x) {
                        if (me.y == enemy.y + 1)
                            command = new AttackCommand(Direction.UP, Weapon.SWORD);
                        else
                            command = new AttackCommand(Direction.DOWN, Weapon.SWORD);
                    }
                    else {
                        if (me.x == enemy.x + 1)
                            command = new AttackCommand(Direction.LEFT, Weapon.SWORD);
                        else
                            command = new AttackCommand(Direction.RIGHT, Weapon.SWORD);
                    }
                }
                else
                    command = PathHelper.getNextMove(enemy.x, enemy.y);
                break;
            case ATTACK_BUILDING:
                //TODO: Find appropriate enemy building
                break;
        }

        return command.getCommandCode();
    }

    public void initSubState() {
        int enemyPower = Helpers.getEnemyAttackingPower();
        int myPower = Helpers.getMyAttackingPower();
        if (enemyPower > myPower)
            attackSubState = AttackSubState.ATTACK_BUILDING;
        else
            attackSubState = AttackSubState.ATTACK_PLAYER;
    }

}

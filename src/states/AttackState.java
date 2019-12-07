package states;

import bot.Bot;
import bot.Helpers;
import bot.PathHelper;
import commands.AttackCommand;
import commands.Command;
import commands.enums.Direction;
import commands.enums.Weapon;
import constants.WeaponType;
import models.Player;
import models.items.Item;

public class AttackState extends State {
    enum AttackSubState {
        ATTACK_PLAYER,
        ATTACK_BUILDING
    }

    private static AttackState attack_state = null;
    private AttackSubState attackSubState = AttackSubState.ATTACK_BUILDING;
    private AttackState(Bot bot) { this.bot = bot;}
    private Item attackingBuilding = null;

    public static AttackState getInstance(Bot bot)
    {
        if (attack_state == null)
            attack_state = new AttackState(bot);

        return attack_state;
    }

    @Override
    public String chooseAction() {

        Command command = null;

        if (Helpers.getMyAttackingPower() < 10) {
            bot.changeState(StateEnum.BUILD);
            return BuildState.getInstance(bot).chooseAction();
        }
        Player me = null;
        switch (attackSubState) {
            case ATTACK_PLAYER:
                me = Helpers.returnMe();
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
                if (attackingBuilding == null) {
                    attackingBuilding = Helpers.GetNearestEnemyBuilding();
                    if (attackingBuilding == null) {
                        attackSubState = AttackSubState.ATTACK_PLAYER;
                        return this.chooseAction();
                    }
                }

                me = Helpers.returnMe();
                if (Helpers.EnemyBuildingReachableBySword(attackingBuilding)) {
                    if (me.x == attackingBuilding.x) {
                        if (me.y == attackingBuilding.y + 1)
                            command = new AttackCommand(Direction.UP, Weapon.SWORD);
                        else
                            command = new AttackCommand(Direction.DOWN, Weapon.SWORD);
                    }
                    else {
                        if (me.x == attackingBuilding.x + 1)
                            command = new AttackCommand(Direction.LEFT, Weapon.SWORD);
                        else
                            command = new AttackCommand(Direction.RIGHT, Weapon.SWORD);
                    }

                    if (Helpers.assessEnemyDanger()) {
                        if (Helpers.getMyAttackingPower() > Helpers.getEnemyAttackingPower())
                            attackSubState = AttackSubState.ATTACK_PLAYER;
                        else
                            bot.changeState(StateEnum.RETREAT);
                    }
                }
                else {
                    command = PathHelper.getNextMove(attackingBuilding.x, attackingBuilding.y);
                }


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

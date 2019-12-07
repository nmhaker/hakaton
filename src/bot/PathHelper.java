package bot;

import commands.Command;
import commands.MoveCommand;
import commands.enums.Direction;
import models.Game;
import models.Player;
import models.Tile;

import java.util.Stack;

public class PathHelper {
    public static  class Coordinate {
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
            dist = 0;
        }

        public Coordinate(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public int x;
        public int y;
        public int dist;
    }

    public static Stack<Direction> moveHistory = new Stack<>();
    public static Stack<Direction> recoveryStack = new Stack<>();

    public static boolean barrierAvoidance = false;

    public static Game game;
    public static int playerID;

    public static int getPlayerID() {
        return playerID;
    }

    public static void setPlayerID(int playerID) {
        PathHelper.playerID = playerID;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        PathHelper.game = game;
    }

    public static Player returnMe(){
        return game.result.player1.id == playerID ? game.result.player1 : game.result.player2;
    }

    public static Command getNextMove(int dstx, int dsty) { //cx, cy -> current x and y, dx and dy -> destination x and y
        int cx = returnMe().x;
        int cy = returnMe().y;
        int dx = cx - dstx;
        int dy = cy - dsty;
        MoveCommand command = null;
        Tile[][] tiles = game.result.map.tiles;
        Direction lastMove = moveHistory.empty() ? null : moveHistory.peek();
        Direction lastMoveInv =lastMove == Direction.LEFT ? Direction.RIGHT : lastMove == Direction.RIGHT ? Direction.LEFT : lastMove == Direction.UP ? Direction.DOWN : Direction.UP;
       if (dx != 0) {
            if (dx > 0) {
                if (freeField(tiles, cy,cx - 1) && (recoveryStack.empty() || recoveryStack.peek() != Direction.LEFT) && (moveHistory.empty() || lastMoveInv != Direction.LEFT)) {
                    barrierAvoidance = false;
                    recoveryStack.clear();
                    command = new MoveCommand(Direction.LEFT);
                }
                else {
                    command = xBarrier(game, cx, cy, dy, lastMoveInv);
                }
            }
            else {
                if (freeField(tiles, cy,cx + 1)&& (recoveryStack.empty() || recoveryStack.peek() != Direction.RIGHT)&& (moveHistory.empty() || lastMoveInv != Direction.RIGHT)) {
                    barrierAvoidance = false;
                    recoveryStack.clear();
                    command = new MoveCommand(Direction.RIGHT);
                }
                else {
                    command = xBarrier(game, cx, cy, dy, lastMoveInv);
                }
            }
        }
        else {
           if (dy != 0) {
               if (dy > 0) {
                   if (freeField(tiles, cy - 1, cx) && (recoveryStack.empty() || recoveryStack.peek() != Direction.UP) && (moveHistory.empty() || lastMoveInv != Direction.UP)) {
                       barrierAvoidance = false;
                       recoveryStack.clear();
                       command = new MoveCommand(Direction.UP);
                   } else {
                       command = yBarrier(game, cx, cy, dx, lastMoveInv);
                   }
               } else {
                   if (freeField(tiles, cy + 1, cx) && (recoveryStack.empty() || recoveryStack.peek() != Direction.DOWN) && (moveHistory.empty() || lastMoveInv != Direction.DOWN)) {
                       barrierAvoidance = false;
                       recoveryStack.clear();
                       command = new MoveCommand(Direction.DOWN);
                   } else {
                       command = yBarrier(game, cx, cy, dx, lastMoveInv);
                   }
               }
           }
       }

        if (command == null)
            return new MoveCommand(Direction.NO_DIRECTION);

        if (!barrierAvoidance) moveHistory.push(command.getDirection());
        return command;
    }

    public static boolean freeField(Tile[][] tiles, int y, int x) {
        Player enemy = Helpers.returnEnemy();
        return !tiles[y][x].entity && !tiles[y][x].shop && !tiles[y][x].buildingInProcess && tiles[y][x].item == null && (enemy.y != y || enemy.x != x);
    }
    private static MoveCommand xBarrier(Game game, int cx, int cy, int dy, Direction invDirection) {
        if (cy != 0 && freeField(game.result.map.tiles, cy-1, cx) && Direction.UP != invDirection) {
            barrierAvoidance = false;
            recoveryStack.clear();
            return new MoveCommand(Direction.UP);
        }
        if (cy != 19 && freeField(game.result.map.tiles, cy+1, cx) && Direction.DOWN != invDirection) {
            barrierAvoidance = false;
            recoveryStack.clear();
            return new MoveCommand(Direction.DOWN);
        }

        barrierAvoidance = true;
        Direction rev = moveHistory.pop();
        recoveryStack.push(rev);

        return new MoveCommand(rev == Direction.LEFT ? Direction.RIGHT : rev == Direction.RIGHT ? Direction.LEFT : rev == Direction.UP ? Direction.UP : Direction.DOWN);
//        if (game.result.map.tiles[cy][cx+1].entity)
//            return new MoveCommand(Direction.RIGHT);
//        else
//            return new MoveCommand(Direction.LEFT);
    }

    private static MoveCommand yBarrier(Game game, int cx, int cy, int dx, Direction invDirection) {
        if (dx != 0 && freeField(game.result.map.tiles, cy, cx - 1) && Direction.LEFT != invDirection) {
            barrierAvoidance = false;
            recoveryStack.clear();
            return new MoveCommand(Direction.LEFT);
        }
        if (dx != 24 && !game.result.map.tiles[cy +1][cx].entity && Direction.RIGHT != invDirection) {
            barrierAvoidance = false;
            recoveryStack.clear();
            return new MoveCommand(Direction.RIGHT);
        }
        barrierAvoidance = true;
        Direction rev = moveHistory.pop();
        recoveryStack.push(rev);

        return new MoveCommand(rev == Direction.LEFT ? Direction.RIGHT : rev == Direction.RIGHT ? Direction.LEFT : rev == Direction.UP ? Direction.UP : Direction.DOWN);
//
//        if (game.result.map.tiles[cy+1][cx].entity)
//            return new MoveCommand(Direction.DOWN);
//        else
//            return new MoveCommand(Direction.UP);
    }
}

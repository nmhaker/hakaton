package bot;

import commands.Command;
import commands.MoveCommand;
import commands.enums.Direction;
import models.Game;
import models.Player;
import models.Tile;

public class PathHelper {    public static  class Coordinate {
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
        Tile[][] tiles = game.result.map.tiles;
        if (dx != 0) {
            if (dx > 0) {
                if (freeField(tiles, cy,cx - 1)) {
                    return new MoveCommand(Direction.LEFT);
                }
                else {
                    return xBarrier(game, cx, cy, dy);
                }
            }
            else {
                if (freeField(tiles, cy,cx + 1)) {
                    return new MoveCommand(Direction.RIGHT);
                }
                else {
                    return xBarrier(game, cx, cy, dy);
                }
            }
        }

        if (dy != 0) {
            if (dy > 0) {
                if (freeField(tiles, cy - 1, cx)) {
                    return new MoveCommand(Direction.UP);
                }
                else {
                    return yBarrier(cx, cy, dx);
                }
            }
            else {
                if (freeField(tiles,cy + 1,cx)) {
                    return new MoveCommand(Direction.DOWN);
                }
                else {
                    return yBarrier(cx, cy, dx);
                }
            }
        }

        return new MoveCommand(Direction.NO_DIRECTION);
    }

    public static boolean freeField(Tile[][] tiles, int y, int x) {
        return !tiles[y][x].entity && !tiles[y][x].shop && !tiles[y][x].buildingInProcess && tiles[y][x].item == null;
    }
    private static Command xBarrier(Game game, int cx, int cy, int dy) {
        if (dy > 0 && !game.result.map.tiles[cy-1][cx].entity)
            return new MoveCommand(Direction.UP);
        if (dy < 0 && !game.result.map.tiles[cy+1][cx].entity)
            return new MoveCommand(Direction.DOWN);

        if (game.result.map.tiles[cy][cx+1].entity)
            return new MoveCommand(Direction.RIGHT);
        else
            return new MoveCommand(Direction.LEFT);
    }

    private static Command yBarrier(int cx, int cy, int dx) {
        if (dx > 0 && !game.result.map.tiles[cy][cx - 1].entity)
            return new MoveCommand(Direction.LEFT);
        if (dx < 0 && !game.result.map.tiles[cy +1][cx].entity)
            return new MoveCommand(Direction.RIGHT);

        if (game.result.map.tiles[cy+1][cx].entity)
            return new MoveCommand(Direction.DOWN);
        else
            return new MoveCommand(Direction.UP);
    }
}

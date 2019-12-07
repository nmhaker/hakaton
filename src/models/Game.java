package models;

public class Game {
    public boolean success;

    public Result result;

    public Integer playerIndex;

    public String message;

    public boolean tileFree(int x, int y) {
        return !result.map.tiles[x][y].entity;
    }

}

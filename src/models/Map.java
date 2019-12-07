package models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Map {
    public Integer height;

    public Integer width;

    public Tile[][] tiles;
}

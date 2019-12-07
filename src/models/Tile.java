package models;

import models.items.Item;

public class Tile {

    public Item item;

    public boolean buildingInProcess;

    public boolean entity;

    public boolean shop;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

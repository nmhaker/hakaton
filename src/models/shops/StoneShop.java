package models.shops;

import constants.ItemType;
import models.Item;

public class StoneShop extends Item {

    public Integer x;

    public Integer y;

    public boolean empty;

    public String itemType = ItemType.STONE_SHOP;
}

package models.items.shops;

import constants.ItemType;
import models.items.Item;

public class StoneShop extends Item {

    public Integer value;

    public boolean empty;

    public String itemType = ItemType.STONE_SHOP;

    public String GetItemType(){
        return itemType;
    }
}

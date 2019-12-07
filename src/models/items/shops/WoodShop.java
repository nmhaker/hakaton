package models.items.shops;

import constants.ItemType;
import models.items.Item;

public class WoodShop extends Item {

    public Integer value;

    public boolean empty;

    public String itemType = ItemType.WOOD_SHOP;

    public String GetItemType(){
        return itemType;
    }
}

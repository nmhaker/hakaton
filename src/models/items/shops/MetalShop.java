package models.items.shops;

import constants.ItemType;
import models.items.Item;

public class MetalShop extends Item {

    public Integer value;

    public boolean empty;

    public String itemType = ItemType.METAL_SHOP;

    public String GetItemType(){
        return itemType;
    }
}

package models.items.fortresses;

import constants.ItemType;
import models.items.Item;

public class Fortress extends Item {

    public Integer id;

    public Integer health;

    public String itemType = ItemType.FORTRESS;

    public String GetItemType(){
        return itemType;
    }

}

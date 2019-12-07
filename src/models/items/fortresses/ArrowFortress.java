package models.items.fortresses;

import constants.ItemType;
import models.items.Item;

public class ArrowFortress extends Item {

    public Integer id;

    public Integer x;

    public Integer y;

    public Integer health;

    public Integer timeSinceBuilding;

    public Integer numWeapons;

    public Integer timeSinceMakeWeapon;

    public String itemType = ItemType.ARROW_FORTRESS;

    public String stringType;

    public String GetItemType(){
        return itemType;
    }

}

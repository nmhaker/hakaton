package models.items;

import constants.ItemType;
import models.items.Item;

public class House extends Item {

    public Integer id;

    public Integer health;

    public String itemType = ItemType.HOUSE;

    public Integer timeSinceBuilding;

    public Integer numWeapons;

    public Integer timeSinceMakeWeapon;

    public String stringType;

    public String GetItemType(){
        return itemType;
    }

}

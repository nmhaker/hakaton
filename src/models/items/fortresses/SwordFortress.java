package models.items.fortresses;

import constants.ItemType;
import models.items.Item;

public class SwordFortress extends Item {
    public Integer id;

    public Integer x;

    public Integer y;

    public Integer health;

    public Integer timeSinceBuilding;

    public Integer numWeapons;

    public Integer timeSinceMakeWeapon;

    public String itemType = ItemType.SWORD_FORTRESS;

    public String stringType;
}

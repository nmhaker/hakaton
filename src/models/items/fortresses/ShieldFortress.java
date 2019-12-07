package models.items.fortresses;

import constants.ItemType;
import models.items.Item;

public class ShieldFortress extends Item {
    public Integer id;

    public Integer health;

    public Integer timeSinceBuilding;

    public Integer numWeapons;

    public Integer timeSinceMakeWeapon;

    public String itemType = ItemType.SHIELD_FORTRESS;

    public String stringType;
}

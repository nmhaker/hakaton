package models;

import constants.ItemType;
import constants.StringType;

public class ArrowFortress {

    public Integer id;

    public Integer x;

    public Integer y;

    public Integer health;

    public Integer timeSinceBuilding;

    public Integer numWeapons;

    public Integer timeSinceMakeWeapon;

    public String itemType = ItemType.ARROW_FORTRESS;

    public StringType stringType;

}

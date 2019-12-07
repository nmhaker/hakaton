package models;

import constants.ItemType;
import models.items.Item;

public class House extends Item {
    public Integer x;

    public Integer y;

    public Integer id;

    public Integer health;

    public String itemType = ItemType.HOUSE;
}

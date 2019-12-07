package models;

import models.items.Item;
import models.weapons.Weapon;

public class Player {

    public Integer id;

    public Integer x;

    public Integer y;

    public Integer health;

    public Integer initX;

    public Integer initY;

    public Integer lives;

    public Integer kills;

    public Integer score;

    public Weapon weapon1;

    public Weapon weapon2;

    public Integer stupidMoves;

    public Resources resources;

    public  Item[] notFinishedBuildings;

    public Item[] buildings;

    public String stringType;
}

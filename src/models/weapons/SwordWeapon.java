package models.weapons;

import constants.WeaponType;

public class SwordWeapon extends Weapon {

    public String type = WeaponType.SWORD;
    public String getType() {
        return type;
    }
    public Integer swings;
}

package models.weapons;

import constants.WeaponType;

public class ShieldWeapon extends Weapon {

    public String type = WeaponType.SHIELD;
    public String getType() {
        return type;
    }
    public Integer health;

}

package models.weapons;

import constants.ItemType;
import constants.WeaponType;

public class ArrowWeapon extends Weapon {

    public String type = WeaponType.ARROW;

    public String getType() {
        return type;
    }
    public Integer num_of_arrows;
}

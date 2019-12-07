package models.weapons;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import constants.WeaponType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ArrowWeapon.class, name = "Arrow"),
        @JsonSubTypes.Type(value = ShieldWeapon.class, name = "Shield"),
        @JsonSubTypes.Type(value = SwordWeapon.class, name = "Sword")

})
public abstract class Weapon {
    public abstract String getType();
}

package models.weapons;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import constants.WeaponType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ArrowWeapon.class, name = "ARROW"),
        @JsonSubTypes.Type(value = ShieldWeapon.class, name = "SHIELD"),
        @JsonSubTypes.Type(value = SwordWeapon.class, name = "SWORD")

})
public abstract class Weapon {
    public String type;


}

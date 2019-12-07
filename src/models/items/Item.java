package models.items;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import models.House;
import models.items.fortresses.ArrowFortress;
import models.items.fortresses.Fortress;
import models.items.fortresses.ShieldFortress;
import models.items.shops.MetalShop;
import models.items.shops.StoneShop;
import models.items.shops.WoodShop;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "itemType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MetalShop.class, name = "METAL_SHOP"),
        @JsonSubTypes.Type(value = WoodShop.class, name = "WOOD_SHOP"),
        @JsonSubTypes.Type(value = StoneShop.class, name = "STONE_SHOP"),
        @JsonSubTypes.Type(value = ArrowFortress.class, name = "ARROW_FORTRESS"),
        @JsonSubTypes.Type(value = ShieldFortress.class, name = "SHIELD_FORTRESS"),
        @JsonSubTypes.Type(value = Fortress.class, name = "FORTRESS"),
        @JsonSubTypes.Type(value = House.class, name = "HOUSE")
})
public abstract class Item {

}

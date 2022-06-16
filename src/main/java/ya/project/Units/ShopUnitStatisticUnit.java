package ya.project.Units;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShopUnitStatisticUnit {

    @JsonProperty("id")
    @NonNull
    private String id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("parentId")
    private String parentId;

    @JsonProperty("type")
    @NotNull
    private ShopUnitType type;

    @JsonProperty("price")
    private int price;

    @JsonProperty("date")
    @NotNull
    private String date;

    public ShopUnitStatisticUnit(ShopUnit shopUnit) {
        this.id =shopUnit.getId();
        this.name = shopUnit.getName();
        this.price = shopUnit.getPrice();
        this.parentId = shopUnit.getParentId();
        this.type = shopUnit.getType();
        this.date = shopUnit.getDate();
    }

}

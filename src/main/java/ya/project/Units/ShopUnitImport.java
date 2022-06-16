package ya.project.Units;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShopUnitImport {

    @JsonProperty("id")
    @NonNull
    private String id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("parentId")
    private String parentId;

    @JsonProperty("type")
    @NotNull
    private ShopUnitType type;

}

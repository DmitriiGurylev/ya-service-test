package ya.project.Units;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ShopUnitImportRequest {

    @JsonProperty("items")
    List<ShopUnitImport> shopUnits;

    @JsonProperty("updateDate")
    String updateDate;

}

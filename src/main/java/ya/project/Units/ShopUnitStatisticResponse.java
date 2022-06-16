package ya.project.Units;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShopUnitStatisticResponse {

    @JsonProperty("items")
    private List<ShopUnitStatisticUnit> items = new LinkedList<>();

}

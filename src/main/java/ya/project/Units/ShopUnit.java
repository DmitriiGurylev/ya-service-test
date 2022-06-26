package ya.project.Units;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class ShopUnit {

    @JsonProperty("id")
    @Id
    @NonNull
    private String id;

    @JsonProperty("name")
    @NotNull
    @Column(nullable=false)
    private String name;

    @JsonProperty("parentId")
    @Column
    private String parentId;

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    @Column
    @NotNull
    private ShopUnitType type;

    @JsonProperty("price")
    @Column
    private Integer price;

    @JsonProperty("date")
    private String date;

    @JsonProperty("children")
    @Transient
    private  List<ShopUnit> children = null;

    public ShopUnit (ShopUnitImport shopUnitImport) {
        this.id =shopUnitImport.getId();
        this.name = shopUnitImport.getName();
        this.price = shopUnitImport.getPrice();
        this.parentId = shopUnitImport.getParentId();
        this.type = shopUnitImport.getType();
    }

}

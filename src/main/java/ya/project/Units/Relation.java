package ya.project.Units;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "relation")
@Getter
public class Relation {

    @EmbeddedId()
    @NotNull
    private Key key = new Key();

    public Relation(String parentId, String childId) {
        key = new Key();
        key.parentId = parentId;
        key.childId = childId;
    }

    @AllArgsConstructor @NoArgsConstructor
    @Embeddable
    @Getter
    public static class Key implements Serializable {

        @Column(nullable = false)
        @NotNull
        private String parentId;

        @Column(nullable = false)
        @NotNull
        private String childId;
    }

}

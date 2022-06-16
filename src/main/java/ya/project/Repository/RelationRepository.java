package ya.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ya.project.Units.Relation;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationRepository extends CrudRepository<Relation, String> {

    List<Relation> findByKeyParentId(String parentId);

    void deleteByKeyParentId(String parentId);

    Optional<Relation> findByKeyParentIdAndKeyChildId(String parentId, String childId);

    void deleteByKeyParentIdAndKeyChildId(String parentId, String childId);

}

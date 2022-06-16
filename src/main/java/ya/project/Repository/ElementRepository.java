package ya.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ya.project.Units.ShopUnit;
import ya.project.Units.ShopUnitType;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Repository
public interface ElementRepository extends CrudRepository<ShopUnit, String> {

    List<ShopUnit> findAllByTypeAndDateBetween(ShopUnitType type, String start, String to);

}

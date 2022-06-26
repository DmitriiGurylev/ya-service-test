package ya.project.Service;

import ya.project.Help.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ya.project.Units.*;
import ya.project.Repository.ElementRepository;
import ya.project.Repository.RelationRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    ElementRepository elementRepository;
    @Autowired
    RelationRepository relationRepository;

    public ShopUnit getInfoOfItemAndItsChildrenById(String id) {
        Optional<ShopUnit> itemOpt = elementRepository.findById(id);
        if (itemOpt.isPresent()) {
            ShopUnit shopUnit = itemOpt.get();
            findChildrenAsHierarchy(shopUnit);
            log.info("Info has been got from database");
            return shopUnit;
        }
        log.warn("ID IS NOT IN DATABASE!!!");
        return null;
    }

    public boolean deleteItemByIdAndChildren(String id) {
        Optional<ShopUnit> unitToRemoveOpt = elementRepository.findById(id);
        if (unitToRemoveOpt.isEmpty()) {
            return false;
        }
        ShopUnit shopUnitToRemove = unitToRemoveOpt.get();
        String parentId = shopUnitToRemove.getParentId();
        if (parentId!=null) {
            relationRepository.deleteByKeyParentIdAndKeyChildId(parentId, id);
        }
        List<String> children = findAllChildrenAsListStructure(shopUnitToRemove.getId());

        Optional<ShopUnit> itemToFindAveragePrice = elementRepository.findById(getHighestParentId(id));

        elementRepository.deleteById(id);
        relationRepository.deleteByKeyParentId(id);
        children.forEach(c -> elementRepository.deleteById(c));
        children.forEach(c -> relationRepository.deleteByKeyParentId(c));

        if (itemToFindAveragePrice.isPresent()) {
            setAveragePriceOfCategory(itemToFindAveragePrice.get().getId());
        } else {
            log.warn("ROOT ITEM DOESN'T EXIST!!!");
            throw new RuntimeException("Item doesn't exist");
        }
        return true;
    }

    private boolean validateShopUnitsWhileImport(List<ShopUnitImport> shopUnitImports) {
        if (shopUnitImports == null) {
            log.warn("SHOP UNIT IMPORTS OBJECT IS NULL");
            return false;
        }
        for (ShopUnitImport su : shopUnitImports) {
            if (su.getType()==null) {
                log.warn("CATEGORY IS NULL");
                return false;
            }
            if (su.getParentId()!=null && su.getParentId().isEmpty()) {
                return false;
            }
            if (su.getType().equals(ShopUnitType.CATEGORY)) {
                if (su.getPrice() != null || su.getName() == null) {
                    log.warn("CATEGORY HAS INCORRECT PRICE");
                    return false;
                }
            } else if (su.getPrice() == null || su.getPrice() < 0) {
                log.warn("OFFER HAS INCORRECT PRICE");
                return false;
            }
        }
        return true;
    }

    public boolean importItems(ShopUnitImportRequest shopUnitImportRequest) {
        if (shopUnitImportRequest == null) {
            log.warn("ITEMS ARE NULL");
            return false;
        }
        List<ShopUnitImport> shopUnitImports = shopUnitImportRequest.getShopUnits();
        boolean validated = validateShopUnitsWhileImport(shopUnitImports);
        if (!validated) {
            log.warn("ITEMS ARE NOT VALIDATED");
            return false;
        }
        String updateDate = shopUnitImportRequest.getUpdateDate();

        for (ShopUnitImport shopUnitToImport : shopUnitImports) {
            Optional<ShopUnit> existingItemOpt = elementRepository.findById(shopUnitToImport.getId());
            if (existingItemOpt.isPresent()) {
                ShopUnit su = existingItemOpt.get();
                if (su.getType().equals(shopUnitToImport.getType())) {
                    su.setName(shopUnitToImport.getName());
                    su.setDate(updateDate);
                    su.setPrice(shopUnitToImport.getPrice());
                    su.setParentId(shopUnitToImport.getParentId());
                    Optional<Relation> relationOpt = relationRepository.findByKeyChildId(su.getId());
                    if (relationOpt.isPresent()) {
                        if (!relationOpt.get().getKey().getParentId().equals(su.getParentId())) {
                            relationRepository.deleteByKeyParentIdAndKeyChildId(
                                    relationOpt.get().getKey().getParentId(),
                                    relationOpt.get().getKey().getChildId()
                            );
                        }
                    }
                    relationRepository.save(
                            new Relation(su.getParentId(), su.getId())
                    );
                    elementRepository.save(su);
                } else {
                    log.warn("UNEQUAL TYPES");
                    return false;
                }
            } else {
                ShopUnit su = new ShopUnit(shopUnitToImport);
                String parentId = shopUnitToImport.getParentId();
                if (parentId!=null) {
                    elementRepository.findById(parentId).ifPresent(item ->
                            relationRepository.save(
                                    new Relation(parentId, su.getId()))
                    );
                }
                su.setDate(updateDate);
                elementRepository.save(su);
            }
            Optional<ShopUnit> itemToFindAveragePrice = elementRepository.findById(getHighestParentId(shopUnitToImport.getId()));
            if (itemToFindAveragePrice.isPresent()) {
                setAveragePriceOfCategory(itemToFindAveragePrice.get().getId());
            } else {
                throw new RuntimeException("Item doesn't exist");
            }
        }
        log.info("items updated");
        return true;
    }

    private String getHighestParentId(String currentItemId) {
        Optional<ShopUnit> item = elementRepository.findById(currentItemId);
        if (item.isPresent()) {
            String parentId = item.get().getParentId();
            if (parentId == null) return currentItemId;
            String highestParent = getHighestParentId(item.get().getParentId());
            return highestParent.equals("null-1") ?
                    currentItemId :
                    highestParent;
        } else {
            log.warn("ITEM DOESN'T EXIST");
            return "null-1";
        }
    }

    private void findChildrenAsHierarchy(ShopUnit shopUnit) {
        String str_date = shopUnit.getDate();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date;
        try {
            date = formatter.parse(str_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        shopUnit.setDate(DateHelper.fromDate(date));

        if (shopUnit.getType().equals(ShopUnitType.CATEGORY)) {
            if (shopUnit.getChildren()==null) {
                shopUnit.setChildren(new LinkedList<>());
            }
            List<Relation> relationList = relationRepository.findByKeyParentId(shopUnit.getId());
            List<String> childrenId = relationList.stream()
                    .map(r -> r.getKey().getChildId())
                    .collect(Collectors.toList());
            childrenId.stream()
                    .map(child -> elementRepository.findById(child))
                    .filter(Optional::isPresent)
                    .forEach(ch -> {
                        shopUnit.getChildren().add(ch.get());
                        findChildrenAsHierarchy(ch.get());
                    });
        }
    }

    private List<String> setAveragePriceOfCategory(String id) {
        Optional<ShopUnit> item = elementRepository.findById(id);
        if (item.isEmpty()) {
            return new LinkedList<>();
        } else if (item.get().getType().equals(ShopUnitType.OFFER)) {
            return List.of(item.get().getId());
        } else {
            List<String> idList = new LinkedList<>();
            ShopUnit category = item.get();
            idList.add(id);

            List<Relation> relationList = relationRepository.findByKeyParentId(id);
            List<String> childrenId = relationList.stream()
                    .map(r -> r.getKey().getChildId())
                    .collect(Collectors.toList());

            if (!childrenId.isEmpty()) {
                for (String child : childrenId) {
                    List<String> usedIds = setAveragePriceOfCategory(child);
                    idList.addAll(usedIds);
                }

                List<ShopUnit> shopUnitList = idList.stream()
                        .map(s -> elementRepository.findById(s))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .filter(it -> it.getType().equals(ShopUnitType.OFFER))
                        .collect(Collectors.toList());

                double priceSum = shopUnitList.stream()
                        .mapToDouble(ShopUnit::getPrice)
                        .sum();
                category.setPrice((int) (priceSum / shopUnitList.size()));
                elementRepository.save(category);
            }
            return idList;
        }
    }

    private List<String> findAllChildrenAsListStructure(String id) {
        List<String> children = new LinkedList<>();
        Optional<ShopUnit> item = elementRepository.findById(id);
        if (item.isPresent() && item.get().getType().equals(ShopUnitType.CATEGORY)) {
            List<Relation> relationList = relationRepository.findByKeyParentId(id);
            List<String> childrenId = relationList.stream()
                    .map(r -> r.getKey().getChildId())
                    .collect(Collectors.toList());
            childrenId.forEach(childId -> {
                children.add(childId);
                children.addAll(findAllChildrenAsListStructure(childId));
            });
        }
        return children;
    }

    public ShopUnitStatisticResponse getSalesOfPast24Hours(String dateStr) {
        Date date;
        try {
            date = DateHelper.toDate(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        dateStr = DateHelper.fromAnotherDate(date);
        Date dateBefore = new Date(date.getTime() - TimeUnit.HOURS.toMillis(24));
        String dateBeforeStr = DateHelper.fromAnotherDate(dateBefore);
        List<ShopUnit> itemsList = elementRepository.findAllByTypeAndDateBetween(
                ShopUnitType.OFFER,
                dateBeforeStr,
                dateStr
        );
        List<ShopUnitStatisticUnit> items = itemsList.stream()
                .map(ShopUnitStatisticUnit::new)
                .collect(Collectors.toList());
        return new ShopUnitStatisticResponse(items);
    }
}

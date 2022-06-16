package ya.project.Controllers;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ya.project.Units.ShopUnit;
import ya.project.Units.ShopUnitImportRequest;
import ya.project.Service.Service;
import ya.project.Units.ShopUnitStatisticResponse;
import ya.project.httpCodes.Error;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    Service service;

    @GetMapping(value = "nodes/{id}", produces = "application/json")
    public ResponseEntity<?> getItem(@PathVariable(name = "id") String id) {
        if (id==null || id.equals("") || id.contains(" ")) {
            return new ResponseEntity<>(new Error(404, "Validation Failed"), HttpStatus.BAD_REQUEST);
        }
        final ShopUnit shopUnit = service.getInfoOfItemAndItsChildrenById(id);
        return shopUnit != null ?
                new ResponseEntity<>(shopUnit, HttpStatus.OK) :
                new ResponseEntity<>(new Error(404, "Item not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "imports/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> importItems(@RequestBody ShopUnitImportRequest shopUnitImportRequest) {
        boolean imported = service.importItems(shopUnitImportRequest);
        return imported ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") String id) {
        if (id==null || id.equals("") || id.contains(" ")) {
            return new ResponseEntity<>(new Error(404, "Validation Failed"), HttpStatus.BAD_REQUEST);
        }
        boolean deleted = service.deleteItemByIdAndChildren(id);

        return deleted ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(new Error(404, "Item not found"), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "sales/{date}", produces = "application/json")
    public ResponseEntity<?> salesOfPast24Hours(@PathVariable(name = "date") String date) {
        final ShopUnitStatisticResponse response = service.getSalesOfPast24Hours(date);
        return response == null ?
                new ResponseEntity<>("no",HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(response, HttpStatus.OK);
    }

}

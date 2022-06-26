package ya.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ya.project.Repository.ElementRepository;
import ya.project.Repository.RelationRepository;
import ya.project.Service.Service;
import ya.project.Units.Relation;
import ya.project.Units.ShopUnitImportRequest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestImportInvalid {

    @Autowired
    Service service;
    @Autowired
    ElementRepository elementRepository;
    @Autowired
    RelationRepository relationRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @org.junit.jupiter.api.Test
    public void failImport1() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/import/importInvalid1.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void failImport2()  {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/import/importInvalid2.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void failImport3() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/import/importInvalid3.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void failImport4() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/import/importInvalid4.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void failImport5() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/import/importInvalid5.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void failImport6() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/import/importInvalid6.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void failImport7() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/import/importInvalid7.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void failImport8() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/import/import/importInvalid8.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    @AfterAll
    @Transactional
    public void afterAll() {
        Arrays.asList("1test", "2test", "3test", "4test", "5test", "6test", "7test", "8test")
                .forEach(s -> elementRepository.findById(s).ifPresent(unit -> service.deleteItemByIdAndChildren(s)));
    }

}

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

    @BeforeAll
    public void beforeAll() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/importInValid1.json"), ShopUnitImportRequest.class
        );
    }

    @org.junit.jupiter.api.Test
    public void successImport1() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/importInValid1.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport2()  {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/importInValid2.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport3() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/importInValid3.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport4() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/importInValid4.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport5() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/importInValid5.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport6() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/importInValid6.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport7() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/importInValid7.json"), ShopUnitImportRequest.class
            );
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertFalse(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport8() {
        ShopUnitImportRequest itemsToImport = null;
        try {
            itemsToImport = objectMapper.readValue(
                    new File("src/test/resources/importInValid8.json"), ShopUnitImportRequest.class
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
        Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8").forEach(s -> {
           elementRepository.findById(s).ifPresent(unit -> elementRepository.deleteById(s));
            for (Relation unit : relationRepository.findByKeyParentId(s)) {
                relationRepository.deleteByKeyParentId(unit.getKey().getParentId());
            }
        });
    }

}

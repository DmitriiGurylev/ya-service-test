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
        elementRepository.findById("1").ifPresent(unit -> elementRepository.deleteById("1"));
        relationRepository.findByKeyParentId("1")
                .forEach(unit -> relationRepository.deleteByKeyParentId(unit.getKey().getParentId()));
        elementRepository.findById("2").ifPresent(unit -> elementRepository.deleteById("2"));
        relationRepository.findByKeyParentId("2")
                .forEach(unit -> relationRepository.deleteByKeyParentId(unit.getKey().getParentId()));
        elementRepository.findById("3").ifPresent(unit -> elementRepository.deleteById("3"));
        relationRepository.findByKeyParentId("3")
                .forEach(unit -> relationRepository.deleteByKeyParentId(unit.getKey().getParentId()));
        elementRepository.findById("4").ifPresent(unit -> elementRepository.deleteById("4"));
        relationRepository.findByKeyParentId("4")
                .forEach(unit -> relationRepository.deleteByKeyParentId(unit.getKey().getParentId()));
        elementRepository.findById("5").ifPresent(unit -> elementRepository.deleteById("5"));
        relationRepository.findByKeyParentId("5")
                .forEach(unit -> relationRepository.deleteByKeyParentId(unit.getKey().getParentId()));
        elementRepository.findById("6").ifPresent(unit -> elementRepository.deleteById("6"));
        relationRepository.findByKeyParentId("6")
                .forEach(unit -> relationRepository.deleteByKeyParentId(unit.getKey().getParentId()));
        elementRepository.findById("7").ifPresent(unit -> elementRepository.deleteById("7"));
        relationRepository.findByKeyParentId("7")
                .forEach(unit -> relationRepository.deleteByKeyParentId(unit.getKey().getParentId()));
        elementRepository.findById("8").ifPresent(unit -> elementRepository.deleteById("8"));
        relationRepository.findByKeyParentId("8")
                .forEach(unit -> relationRepository.deleteByKeyParentId(unit.getKey().getParentId()));
    }

}

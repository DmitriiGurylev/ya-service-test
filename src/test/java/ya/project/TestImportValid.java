package ya.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ya.project.Repository.ElementRepository;
import ya.project.Repository.RelationRepository;
import ya.project.Service.Service;
import ya.project.Units.ShopUnitImportRequest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestImportValid {

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
                new File("src/test/resources/importValid1.json"), ShopUnitImportRequest.class
        );
    }

    @org.junit.jupiter.api.Test
    public void successImport1() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/importValid1.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertTrue(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport2() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/importValid2.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertTrue(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport3() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/importValid3.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertTrue(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport4() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/importValid4.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertTrue(imported);
    }

    @org.junit.jupiter.api.Test
    public void afterAll() {
        elementRepository.deleteById("1");
        elementRepository.deleteById("2");
        elementRepository.deleteById("3");
        elementRepository.deleteById("4");
        elementRepository.deleteById("5");
        elementRepository.deleteById("6");
        elementRepository.deleteById("7");
        elementRepository.deleteById("8");
        relationRepository.deleteByKeyParentId("1");
        relationRepository.deleteByKeyParentId("2");
        relationRepository.deleteByKeyParentId("3");
        relationRepository.deleteByKeyParentId("4");
        relationRepository.deleteByKeyParentId("5");
        relationRepository.deleteByKeyParentId("6");
        relationRepository.deleteByKeyParentId("7");
        relationRepository.deleteByKeyParentId("8");
    }

}

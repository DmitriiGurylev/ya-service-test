package ya.project;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class TestImportValid {

    @Autowired
    Service service;
    @Autowired
    ElementRepository elementRepository;
    @Autowired
    RelationRepository relationRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @org.junit.jupiter.api.Test
    public void successImport1() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/import/importValid1.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertTrue(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport2() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/import/importValid2.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertTrue(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport3() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/import/importValid3.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertTrue(imported);
    }

    @org.junit.jupiter.api.Test
    public void successImport4() throws IOException {
        ShopUnitImportRequest itemsToImport = objectMapper.readValue(
                new File("src/test/resources/import/importValid4.json"), ShopUnitImportRequest.class
        );
        boolean imported = service.importItems(itemsToImport);
        Assertions.assertTrue(imported);
    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void afterAll() {
        Arrays.asList("1test", "2test", "3test", "4test", "5test", "6test", "7test", "8test")
                .forEach(s -> elementRepository.findById(s).ifPresent(unit -> service.deleteItemByIdAndChildren(s)));
    }

}

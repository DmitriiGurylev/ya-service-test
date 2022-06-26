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
import ya.project.Units.ShopUnitImportRequest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDelete {

    @Autowired
    Service service;
    @Autowired
    ElementRepository elementRepository;
    @Autowired
    RelationRepository relationRepository;
    @Autowired
    private ObjectMapper objectMapper;

    ShopUnitImportRequest itemsToImport;

    @org.junit.jupiter.api.Test
    @BeforeAll
    public void importBeforeTests() throws IOException {
        itemsToImport = objectMapper.readValue(
                new File("src/test/resources/import/importValid1.json"), ShopUnitImportRequest.class
        );
    }

    @org.junit.jupiter.api.Test
    public void successDelete()  {
        service.importItems(itemsToImport);
        boolean deleted = service.deleteItemByIdAndChildren("1");
        Assertions.assertTrue(deleted);
    }

    @org.junit.jupiter.api.Test
    public void failDelete1()  {
        service.importItems(itemsToImport);
        boolean deleted = service.deleteItemByIdAndChildren("");
        Assertions.assertFalse(deleted);
    }

    @org.junit.jupiter.api.Test
    public void failDelete2()  {
        service.importItems(itemsToImport);
        boolean deleted = service.deleteItemByIdAndChildren("111111111111111");
        Assertions.assertFalse(deleted);
    }

    @org.junit.jupiter.api.Test
    @AfterAll
    @Transactional
    public void afterAll() {
        Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8").forEach(s -> {
            elementRepository.findById(s).ifPresent(unit -> service.deleteItemByIdAndChildren(s));
        });
    }

}

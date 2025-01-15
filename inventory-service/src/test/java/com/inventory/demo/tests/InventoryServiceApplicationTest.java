package com.inventory.demo.tests;

import com.inventory.demo.configuration.TestcontainersConfiguration;
import com.inventory.demo.model.Inventory;
import com.inventory.demo.repository.InventoryRepository;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryServiceApplicationTest extends TestcontainersConfiguration {

    @Autowired
    private InventoryRepository repository;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        final Inventory inventoryOne = new Inventory();
        inventoryOne.setSkuCode("nubia_x_5000");
        inventoryOne.setQuantity(15);

        repository.save(inventoryOne);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldReturnInStockTrue() {
        Boolean response = RestAssured.given()
                .queryParam("skuCode", "nubia_x_5000")
                .queryParam("quantity", 14)
                .when()
                .get("/public/api/v1/inventories")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Boolean.class);

        assertTrue(response);
    }

    @Test
    void shouldReturnInStockFalse() {
        Boolean response = RestAssured.given()
                .queryParam("skuCode", "nubia_x_5000")
                .queryParam("quantity", 16)
                .when()
                .get("/public/api/v1/inventories")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Boolean.class);

        assertFalse(response);
    }
}
package com.product.demo.tests;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceApplicationTest {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");

    @LocalServerPort
    private Integer port;

    static {
        mongoDBContainer.start();
    }

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        final String requestBody = """
                {
                  "name": "Test Phone",
                  "description": "Test Description",
                  "price": 100
                }
                """;

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("public/api/v1/products")
                .then()
                .statusCode(201);
    }

    @Test
    void shouldCreateProduct() {
        final String requestBody = """
                {
                  "name": "Phone name",
                  "description": "Mobile Phone",
                  "price": 240
                }
                """;

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("public/api/v1/products")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Phone name"))
                .body("description", Matchers.equalTo("Mobile Phone"))
                .body("price", Matchers.equalTo(240));
    }

    @Test
    @Disabled
    void shouldGetProductById() {
        final String createProductId =
                RestAssured.given()
                        .when()
                        .get("public/api/v1/products")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getString("[0].id");

        System.out.println("GET_PRODUCT_TEST.INFO: Проверка получения продукта по productId: " + createProductId);

        RestAssured.given()
                .when()
                .get("public/api/v1/products/" + createProductId)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Test phone"))
                .body("description", Matchers.equalTo("Mobile Phone"))
                .body("price", Matchers.equalTo(100));

    }
}

package com.order.demo;

import com.order.demo.configuration.TestcontainersConfiguration;
import com.order.demo.repository.OrderRepository;
import com.order.demo.service.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@AutoConfigureWireMock(port = 0)
public class OrderServiceApplicationTest extends TestcontainersConfiguration {

    @Autowired
    private OrderRepository repository;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateOrder() {
        final String requestBody = """
                {
                  "orderNumber": 2,
                  "skuCode": "xiaomi_13",
                  "price": 240,
                  "quantity": 7
                }
                """;

        InventoryClientStub.stubInventoryCall("xiaomi_13", 7);

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("public/api/v1/orders")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("orderNumber", Matchers.equalTo("2"))
                .body("skuCode", Matchers.equalTo("xiaomi_13"))
                .body("price", Matchers.equalTo(240))
                .body("quantity", Matchers.equalTo(7));
    }

    @Test
    void shouldGetOrdersSuccessfully() {
        repository.deleteAll();

        final String requestBody = """
                {
                  "orderNumber": 1,
                  "skuCode": "xiaomi_13",
                  "price": 180,
                  "quantity": 5
                }
                """;

        InventoryClientStub.stubInventoryCall("xiaomi_13", 5);

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("public/api/v1/orders")
                .then()
                .statusCode(201);


        RestAssured.given()
                .when()
                .get("public/api/v1/orders")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(1))
                .body("[0].orderNumber", Matchers.equalTo("1"))
                .body("[0].skuCode", Matchers.equalTo("xiaomi_13"))
                .body("[0].price", Matchers.equalTo(180.0F))
                .body("[0].quantity", Matchers.equalTo(5));
    }
}

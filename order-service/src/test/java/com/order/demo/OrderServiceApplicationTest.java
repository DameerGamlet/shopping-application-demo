package com.order.demo;

import com.order.demo.configuration.TestcontainersConfiguration;
import com.order.demo.repository.OrderRepository;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

public class OrderServiceApplicationTest extends TestcontainersConfiguration {

    @Autowired
    private OrderRepository repository;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        repository.deleteAll();

        final String requestBody = """
                {
                  "orderNumber": 1,
                  "skuCode": "xiaoimi_redmi_11",
                  "price": 180,
                  "quantity": 5
                }
                """;

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("public/api/v1/orders")
                .then()
                .statusCode(201);
    }

    @Test
    void shouldCreateOrder() {
        final String requestBody = """
                {
                  "orderNumber": 2,
                  "skuCode": "xiaoimi_redmi_13",
                  "price": 240,
                  "quantity": 7
                }
                """;

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("public/api/v1/orders")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("orderNumber", Matchers.equalTo("2"))
                .body("skuCode", Matchers.equalTo("xiaoimi_redmi_13"))
                .body("price", Matchers.equalTo(240))
                .body("quantity", Matchers.equalTo(7));
    }

    @Test
    void shouldGetOrdersSuccessfully() {
        RestAssured.given()
                .when()
                .get("public/api/v1/orders")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(1))
                .body("[0].orderNumber", Matchers.equalTo("1"))
                .body("[0].skuCode", Matchers.equalTo("xiaoimi_redmi_11"))
                .body("[0].price", Matchers.equalTo(180.0F))
                .body("[0].quantity", Matchers.equalTo(5));
    }
}

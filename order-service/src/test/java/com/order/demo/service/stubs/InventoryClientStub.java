package com.order.demo.service.stubs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.contract.spec.internal.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@Slf4j
public class InventoryClientStub {

    public static void stubInventoryCall(String skuCode, Integer quantity) {
        final String stubFullUrl = "/public/api/v1/inventories?skuCode=" + skuCode + "&quantity=" + quantity;
        log.info("STUB.CALL: стабируем сервис со следующими параметрами: url = '{}'", stubFullUrl);

        stubFor(get(stubFullUrl)
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true"))

        );
    }
}

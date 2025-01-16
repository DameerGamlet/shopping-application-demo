package com.order.demo.configuration;

import com.order.demo.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${inventory.url}")
    private String INVENTORY_URL;

    @Bean
    public InventoryClient inventoryClient() {
        final RestClient client = RestClient.builder()
                .baseUrl(INVENTORY_URL)
                .build();

        var adapter = RestClientAdapter.create(client);
        var proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        return proxyFactory.createClient(InventoryClient.class);
    }
}

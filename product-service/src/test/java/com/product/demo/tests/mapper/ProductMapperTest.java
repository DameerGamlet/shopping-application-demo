package com.product.demo.tests.mapper;

import com.product.demo.dto.request.ProductRequest;
import com.product.demo.dto.response.ProductResponse;
import com.product.demo.mapper.ProductMapper;
import com.product.demo.mapper.ProductMapperImpl;
import com.product.demo.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductMapperTest {
    private final ProductMapper productMapper = new ProductMapperImpl();

    private static final String PRODUCT_ID = "1";
    private static final String PRODUCT_NAME = "Test Product";
    private static final String PRODUCT_DESCRIPTION = "Description of test product";
    private static final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(99.99);

    @Test
    void testProductRequestToProductMapping() {
        // Arrange
        final ProductRequest productRequest = new ProductRequest(
                PRODUCT_ID,
                PRODUCT_NAME,
                PRODUCT_DESCRIPTION,
                PRODUCT_PRICE
        );

        // Act
        final Product product = productMapper.requestToProduct(productRequest);

        // Assert
        assertNotNull(product);
        assertEquals(productRequest.id(), product.getId());
        assertEquals(productRequest.name(), product.getName());
        assertEquals(productRequest.description(), product.getDescription());
        assertEquals(productRequest.price(), product.getPrice());

        assertNotNull(product.getProductId());
        assertNotNull(product.getCreatedDate());
    }

    @Test
    void testProductToResponseMapping() {
        // Arrange
        final Product product = new Product(
                PRODUCT_ID,
                UUID.randomUUID(),
                PRODUCT_NAME,
                PRODUCT_DESCRIPTION,
                PRODUCT_PRICE,
                LocalDateTime.now()
        );

        // Act
        final ProductResponse productResponse = productMapper.productToResponse(product);

        // Assert
        assertNotNull(productResponse);
        assertEquals(product.getName(), productResponse.name());
        assertEquals(product.getDescription(), productResponse.description());
        assertEquals(product.getPrice(), productResponse.price());
    }
}

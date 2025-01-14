package com.product.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {
    @Id
    private String id;

    @Field("productId")
    private UUID productId = UUID.randomUUID();
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
}

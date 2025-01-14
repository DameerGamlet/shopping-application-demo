package com.order.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID orderId;
    private UUID userId;

    private String orderNumber;

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    private LocalDateTime createdDate;

    public Order(String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
        this.orderNumber = orderNumber;
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
    }

    @PrePersist
    void setup() {
        if (orderId == null) {
            this.orderId = UUID.randomUUID();
        }
        if (createdDate == null) {
            this.createdDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        }
    }
}

package com.order.demo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(String orderNumber, String skuCode, BigDecimal price, Integer quantity,
                            LocalDateTime createdDate, UUID userId) {
}

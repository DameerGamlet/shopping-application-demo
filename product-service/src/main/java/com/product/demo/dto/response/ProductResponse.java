package com.product.demo.dto.response;

import java.math.BigDecimal;

public record ProductResponse(String name, String description, BigDecimal price) {
}

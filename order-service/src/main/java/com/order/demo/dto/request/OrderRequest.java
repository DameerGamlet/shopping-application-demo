package com.order.demo.dto.request;

import java.math.BigDecimal;

public record OrderRequest(String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
}

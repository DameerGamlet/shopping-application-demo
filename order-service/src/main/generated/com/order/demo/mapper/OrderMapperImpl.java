package com.order.demo.mapper;

import com.order.demo.dto.request.OrderRequest;
import com.order.demo.model.Order;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-14T15:07:37+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (BellSoft)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order requstToOrder(OrderRequest request) {
        if ( request == null ) {
            return null;
        }

        String orderNumber = null;
        String skuCode = null;
        BigDecimal price = null;
        Integer quantity = null;

        orderNumber = request.orderNumber();
        skuCode = request.skuCode();
        price = request.price();
        quantity = request.quantity();

        Order order = new Order( orderNumber, skuCode, price, quantity );

        return order;
    }
}

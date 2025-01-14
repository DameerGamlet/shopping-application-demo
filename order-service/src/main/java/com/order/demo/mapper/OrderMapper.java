package com.order.demo.mapper;

import com.order.demo.dto.request.OrderRequest;
import com.order.demo.dto.response.OrderResponse;
import com.order.demo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "userId ", ignore = true)
    Order requstToOrder(OrderRequest request);

    @Mapping(target = "orderNumber", source = "order.orderNumber")
    @Mapping(target = "skuCode", source = "order.skuCode")
    @Mapping(target = "price", source = "order.price")
    @Mapping(target = "quantity", source = "order.quantity")
    @Mapping(target = "createdDate", source = "order.createdDate")
    @Mapping(target = "userId", source = "order.userId")
    OrderResponse orderToResponse(Order order);
}

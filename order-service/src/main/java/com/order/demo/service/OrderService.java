package com.order.demo.service;

import com.order.demo.client.InventoryClient;
import com.order.demo.dto.request.OrderRequest;
import com.order.demo.dto.response.OrderResponse;
import com.order.demo.exception.OrderIsNullException;
import com.order.demo.exception.InventoryBySkuCodeInStockNotFoundException;
import com.order.demo.mapper.OrderMapper;
import com.order.demo.model.Order;
import com.order.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repository;
    private final InventoryClient inventoryClient;

    public OrderResponse placeOrder(OrderRequest request) {
        log.info("CREATE_ORDER.INFO: Запрос на добавление нового заказа со следующими данными: {}", request);

        boolean inStock = inventoryClient.isInStock(request.skuCode(), request.quantity());

        if (inStock) {
            final Order order = mapper.requstToOrder(request);

            if (order != null) {
                final Order saved = repository.save(order);
                log.info("CREATE_ORDER.SUCCESS: Заказ был успешно создал");

                return mapper.orderToResponse(saved);
            } else {
                throw new OrderIsNullException("Невозможно создать новый заказ");
            }
        } else {
            throw new InventoryBySkuCodeInStockNotFoundException("Inventory with skuCode " + request.skuCode() + " is not in stock");
        }
    }

    public List<OrderResponse> getOrders() {
        return repository.findAll()
                .stream()
                .map(mapper::orderToResponse)
                .toList();
    }
}

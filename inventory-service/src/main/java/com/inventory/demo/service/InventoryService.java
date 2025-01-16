package com.inventory.demo.service;

import com.inventory.demo.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    public boolean isInStock(String scuCode, Integer quantity) {
        log.info("GET_INVENTORY_IN_STOCK.START: Запрос на проверку наличия инвентаря на складе");
        return repository.existsBySkuCodeAndQuantityIsGreaterThanEqual(scuCode, quantity);
    }
}

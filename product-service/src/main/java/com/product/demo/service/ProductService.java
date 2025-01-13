package com.product.demo.service;

import com.product.demo.dto.request.ProductRequest;
import com.product.demo.dto.response.ProductResponse;
import com.product.demo.exception.ProductNotFoundException;
import com.product.demo.mapper.ProductMapper;
import com.product.demo.model.Product;
import com.product.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductResponse getProduct(UUID productId) {
        log.info("GET_PRODUCT.REQUEST: Получение пользователя по его id: {}", productId);
        final Product product = repository.findOneByProductId(productId.toString())
                .orElseThrow(() -> new ProductNotFoundException("Проект с id: %s не обнаружен!"));
        return mapper.productToResponse(product);
    }

    public List<ProductResponse> getProducts() {
        log.info("GET_PRODUCT_LIST.REQUEST: Получение списка продуктов");
        return repository.findAll()
                .stream()
                .map(mapper::productToResponse)
                .toList();
    }

    public ProductResponse createProduct(ProductRequest request) {
        log.info("CREATE_PRODUCT.REQUEST: Получен запрос на добавление нового продукта со следующими данными: {}", request);

        final Product productToCreate = mapper.requestToProduct(request);
        final Product created = repository.save(productToCreate);

        log.info(
                "CREATE_PRODUCT.SUCCESS: Продукт был успешно создан в {} с номером {}",
                productToCreate.getCreatedDate(),
                productToCreate.getProductId()
        );
        return mapper.productToResponse(created);
    }

    public List<Product> getProductsNative() {
        return repository.findAll();
    }
}

package com.product.demo.controller;

import com.product.demo.dto.request.ProductRequest;
import com.product.demo.dto.response.ProductResponse;
import com.product.demo.model.Product;
import com.product.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/products")
public class ProductController {

    private final ProductService service;

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable("productId") UUID productId) {
        return service.getProduct(productId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/native")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductsNative() {
        return service.getProductsNative();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return service.createProduct(request);
    }
}

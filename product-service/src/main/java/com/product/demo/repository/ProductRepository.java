package com.product.demo.repository;

import com.product.demo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{ 'productId': ?0 }")
    Optional<Product> findOneByProductId(@Param("productId") String productId);
}

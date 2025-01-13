package com.product.demo.mapper;

import com.product.demo.dto.request.ProductRequest;
import com.product.demo.dto.response.ProductResponse;
import com.product.demo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productId", ignore = true, defaultExpression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "createdDate", ignore = true, defaultExpression = "java(java.time.LocalDateTime.now())")
    Product requestToProduct(ProductRequest request);

    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "price", source = "product.price")
    ProductResponse productToResponse(Product product);
}

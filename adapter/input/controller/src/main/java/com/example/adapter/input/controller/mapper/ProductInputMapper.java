package com.example.adapter.input.controller.mapper;

import com.example.adapter.input.controller.dto.ProductRequest;
import com.example.adapter.input.controller.dto.ProductResponse;
import com.example.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductInputMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "value", target = "value")
    ProductResponse toProductResponse(Product product);

    @Mapping(source = "description", target = "description")
    @Mapping(source = "value", target = "value")
    Product toProduct(ProductRequest request);
}


package com.example.adapter.output.repository.mapper;


import com.example.adapter.output.repository.entity.ProductEntity;
import com.example.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOutputMapper {
    Product toDomain(ProductEntity productEntity);
    ProductEntity toProductEntity(Product product);
}


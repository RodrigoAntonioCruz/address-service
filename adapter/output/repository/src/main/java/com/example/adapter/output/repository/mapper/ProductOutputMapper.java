package com.example.adapter.output.repository.mapper;


import com.example.adapter.output.repository.entity.ProductEntity;
import com.example.domain.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOutputMapper {
    Address toDomain(ProductEntity productEntity);
    ProductEntity toProductEntity(Address product);
}


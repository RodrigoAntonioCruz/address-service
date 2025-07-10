package com.example.adapter.input.controller.mapper;

import com.example.adapter.input.controller.dto.response.AddressResponse;
import com.example.domain.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressInputMapper {
    AddressResponse toResponse(Address address);

}


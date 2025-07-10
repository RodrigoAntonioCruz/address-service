package com.example.adapter.output.client.mapper;

import com.example.adapter.output.client.dto.ViaCepResponse;
import com.example.domain.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressOutputMapper {

    @Mapping(source = "logradouro", target = "street")
    @Mapping(source = "bairro", target = "neighborhood")
    @Mapping(source = "localidade", target = "city")
    @Mapping(source = "uf", target = "state")
    @Mapping(source = "estado", target = "stateFullName")
    @Mapping(source = "regiao", target = "region")
    @Mapping(source = "ibge", target = "ibgeCode")
    @Mapping(source = "ddd", target = "areaCode")
    @Mapping(source = "siafi", target = "siafiCode")
    Address toResponse(ViaCepResponse viaCepResponse);
}


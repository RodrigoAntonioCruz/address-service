package com.example.usecase.ports.output;

import com.example.domain.entities.Address;

public interface FindAddressByCepOutputPort {
    Address findAddressByCep(String cep);
    Address saveAddressAlias(String aliasCep, Address address);
}

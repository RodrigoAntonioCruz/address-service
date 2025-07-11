package com.example.usecase.address.ports.input;

import com.example.domain.entities.Address;

public interface FindAddressByCepInputPort {
    Address findAddressByCep(String cep);
}

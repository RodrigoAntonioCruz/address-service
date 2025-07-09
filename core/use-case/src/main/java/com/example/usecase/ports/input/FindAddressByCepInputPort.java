package com.example.usecase.ports.input;

import com.example.domain.entities.Address;

public interface FindAddressByCepInputPort {
    Address findByCep(String cep);
}

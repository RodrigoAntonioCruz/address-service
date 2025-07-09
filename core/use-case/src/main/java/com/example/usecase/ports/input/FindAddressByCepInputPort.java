package com.example.usecase.ports.input;

import com.example.domain.entities.Address;

public interface FindAddressByCepInputPort {
    Address findAddressByCep(String cep);
}

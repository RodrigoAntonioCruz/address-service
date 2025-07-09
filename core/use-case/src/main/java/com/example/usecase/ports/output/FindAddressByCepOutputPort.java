package com.example.usecase.ports.output;

import com.example.domain.entities.Address;

import java.util.Optional;

public interface FindAddressByCepOutputPort {
    Optional<Address> findByCep(String cep);
}

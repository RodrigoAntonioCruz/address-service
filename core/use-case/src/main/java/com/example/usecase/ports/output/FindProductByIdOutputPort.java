package com.example.domain.ports.output;

import com.example.domain.Product;

import java.util.Optional;

public interface FindProductByIdOutputPort {
    Optional<Product> findById(String id);
}

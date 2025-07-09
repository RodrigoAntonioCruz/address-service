package com.example.usecase.ports.input;

import com.example.domain.Product;

public interface SaveProductInputPort {
    Product save(Product product);

    Product save(String id, Product product);
}

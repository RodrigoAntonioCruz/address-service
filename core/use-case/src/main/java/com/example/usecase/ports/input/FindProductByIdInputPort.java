package com.example.usecase.ports.input;

import com.example.domain.Product;

public interface FindProductByIdInputPort {
    Product findById(String id);
}

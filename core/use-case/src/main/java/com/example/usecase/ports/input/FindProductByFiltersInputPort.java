package com.example.usecase.ports.input;

import com.example.domain.Product;

import java.util.List;

public interface FindProductByFiltersInputPort {
    List<Product> findByFilter(String query);
}

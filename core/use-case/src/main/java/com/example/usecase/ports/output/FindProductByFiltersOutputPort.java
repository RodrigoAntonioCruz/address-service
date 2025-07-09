package com.example.domain.ports.output;

import com.example.domain.Product;

import java.util.List;

public interface FindProductByFiltersOutputPort {
    List<Product> findByFilter(String query);
}

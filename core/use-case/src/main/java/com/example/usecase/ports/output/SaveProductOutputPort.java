package com.example.domain.ports.output;

import com.example.domain.Product;

public interface SaveProductOutputPort {
    Product save(final Product product);
}

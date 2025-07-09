package com.example.domain.product;

import com.example.domain.Product;
import com.example.domain.ports.input.FindProductByFiltersInputPort;
import com.example.domain.ports.output.FindProductByFiltersOutputPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@ApplicationScoped
public class FindProductByFiltersUseCase implements FindProductByFiltersInputPort {

    private final FindProductByFiltersOutputPort findProductByFiltersOutputPort;

    @Inject
    public FindProductByFiltersUseCase(final FindProductByFiltersOutputPort findProductByFiltersOutputPort) {
        this.findProductByFiltersOutputPort = findProductByFiltersOutputPort;
    }

    @Override
    public List<Product> findByFilter(String query) {
        return findProductByFiltersOutputPort.findByFilter(query);
    }
}

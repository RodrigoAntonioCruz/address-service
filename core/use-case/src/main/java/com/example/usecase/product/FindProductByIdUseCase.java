package com.example.domain.product;

import com.example.domain.Product;

import com.example.domain.ports.output.FindProductByIdOutputPort;
import com.example.domain.utils.Constants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class FindProductByIdUseCase implements FindProductByIdInputPort {

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    @Inject
    public FindProductByIdUseCase(final FindProductByIdOutputPort findProductByIdOutputPort) {
        this.findProductByIdOutputPort = findProductByIdOutputPort;
    }
    @Override
    public Product findById(String productId) {
        return findProductByIdOutputPort.findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, productId)));
    }
}

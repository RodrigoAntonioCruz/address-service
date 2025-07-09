package com.example.usecase.product;

import com.example.domain.Product;

import com.example.domain.ports.output.FindProductByIdOutputPort;
import com.example.domain.ports.output.SaveProductOutputPort;
import com.example.usecase.exception.NotFoundException;
import com.example.usecase.ports.input.SaveProductInputPort;
import com.example.usecase.utils.Constants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class SaveProductUseCase implements SaveProductInputPort {
    private final SaveProductOutputPort saveProductOutputPort;
    private final FindProductByIdOutputPort findProductByIdOutputPort;

    @Inject
    public SaveProductUseCase(final SaveProductOutputPort saveProductOutputPort,
                                    FindProductByIdOutputPort findProductByIdOutputPort) {
        this.saveProductOutputPort = saveProductOutputPort;
        this.findProductByIdOutputPort = findProductByIdOutputPort;
    }

    @Override
    public Product save(Product product) {
        product.validate();
        return saveProductOutputPort.save(product);
    }

    @Override
    public Product save(String id, Product product) {
        var existingProduct = findProductByIdOutputPort.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, id)));

        existingProduct.setDescription(product.getDescription());
        existingProduct.setValue(product.getValue());

        return saveProductOutputPort.save(existingProduct);
    }
}

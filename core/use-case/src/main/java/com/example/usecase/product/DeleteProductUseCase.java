package com.example.usecase.product;



import com.example.domain.ports.output.FindProductByIdOutputPort;
import com.example.domain.utils.Constants;
import com.example.usecase.exception.NotFoundException;
import com.example.usecase.ports.input.DeleteProductInputPort;
import com.example.usecase.ports.output.DeleteProductOutputPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class DeleteProductUseCase implements DeleteProductInputPort {
    private final DeleteProductOutputPort deleteProductOutputPort;

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    @Inject
    public DeleteProductUseCase(DeleteProductOutputPort deleteProductOutputPort, FindProductByIdOutputPort findProductByIdOutputPort) {
        this.deleteProductOutputPort = deleteProductOutputPort;
        this.findProductByIdOutputPort = findProductByIdOutputPort;
    }


    @Override
    public void deleteById(String productId) {
        var product = findProductByIdOutputPort.findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, productId)));

        deleteProductOutputPort.deleteById(product.getId());
    }
}

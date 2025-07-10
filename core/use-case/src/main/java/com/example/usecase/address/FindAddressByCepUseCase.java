package com.example.usecase.address;

import com.example.domain.entities.Address;
import com.example.domain.vo.Cep;
import com.example.usecase.exception.NotFoundException;
import com.example.usecase.ports.input.FindAddressByCepInputPort;
import com.example.usecase.ports.output.FindAddressByCepOutputPort;
import com.example.usecase.utils.Constants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@Named
@ApplicationScoped
public class FindAddressByCepUseCase implements FindAddressByCepInputPort {

    private final FindAddressByCepOutputPort findAddressByCepOutputPort;

    @Inject
    public FindAddressByCepUseCase(final FindAddressByCepOutputPort findAddressByCepOutputPort) {
        this.findAddressByCepOutputPort = findAddressByCepOutputPort;
    }

    @Override
    public Address findAddressByCep(String cep) {
        Cep cepVO = new Cep(cep);

        return cepVO.generateFallbacks().stream()
                .map(findAddressByCepOutputPort::findAddressByCep)
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException(String.format(Constants.ADDRESS_NOT_FOUND_MESSAGE, cep))
                );
    }
}

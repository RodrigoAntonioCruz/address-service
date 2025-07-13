package com.example.usecase.address;

import com.example.domain.entities.Address;
import com.example.domain.utils.Constants;
import com.example.domain.vo.Cep;
import com.example.usecase.address.ports.input.FindAddressByCepInputPort;
import com.example.usecase.address.ports.output.FindAddressByCepOutputPort;
import com.example.usecase.exception.NotFoundException;

import java.util.Objects;
import java.util.logging.Logger;

public class FindAddressByCepUseCase implements FindAddressByCepInputPort {

    private static final Logger LOG = Logger.getLogger(FindAddressByCepUseCase.class.getName());

    private final FindAddressByCepOutputPort findAddressByCepOutputPort;

    public FindAddressByCepUseCase(final FindAddressByCepOutputPort findAddressByCepOutputPort) {
        this.findAddressByCepOutputPort = findAddressByCepOutputPort;
    }

    @Override
    public Address findAddressByCep(String cep) {
        Cep cepVO = new Cep(cep);

        LOG.info(() -> String.format(
                "msg=\"Início da busca de um endereço por CEP\" method=findAddressByCep cepOriginal=%s cepNormalizado=%s",
                cep, cepVO.value()
        ));

        // Primeiro tenta o CEP original
        Address address = findAddressByCepOutputPort.findAddressByCep(cepVO.value());
        if (isValidAddress(address)) {
            Address finalAddress = address;
            LOG.info(() -> String.format(
                    "msg=\"Endereço encontrado diretamente pelo CEP\" method=findAddressByCep cep=%s address=%s",
                    cepVO.value(), finalAddress
            ));
            return address;
        }

        // Tenta os fallbacks
        address = cepVO.generateFallbacks().stream()
                .filter(fallbackCep -> !fallbackCep.equals(cepVO.value()))
                .map(findAddressByCepOutputPort::findAddressByCep)
                .filter(this::isValidAddress)
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException(String.format(Constants.ADDRESS_NOT_FOUND_MESSAGE, cep))
                );

        Address finalAddress1 = address;
        LOG.info(() -> String.format(
                "msg=\"Endereço encontrado via fallback\" method=findAddressByCep cep=%s address=%s",
                cep, finalAddress1
        ));

        return handleAddressOrAlias(cepVO.value(), cep, address);
    }

    private Address handleAddressOrAlias(String normalizedCep, String originalCep, Address address) {
        if (!normalizedCep.equals(address.cep())) {
            findAddressByCepOutputPort.saveAddressAlias(originalCep, address);

            LOG.info(() -> String.format(
                    "msg=\"Alias criado para o CEP original\" method=findAddressByCep cepOriginal=%s cepReferenciado=%s",
                    originalCep, address.cep()
            ));
        }
        return address;
    }

    private boolean isValidAddress(Address address) {
        return Objects.nonNull(address)
                && Objects.nonNull(address.cep())
                && Objects.nonNull(address.street());
    }
}

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

import java.util.Objects;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class FindAddressByCepUseCase implements FindAddressByCepInputPort {

    private static final Logger log = Logger.getLogger(FindAddressByCepUseCase.class.getName());

    private final FindAddressByCepOutputPort findAddressByCepOutputPort;

    @Inject
    public FindAddressByCepUseCase(final FindAddressByCepOutputPort findAddressByCepOutputPort) {
        this.findAddressByCepOutputPort = findAddressByCepOutputPort;
    }

    @Override
    public Address findAddressByCep(String cep) {
        Cep cepVO = new Cep(cep);

        log.info(() -> String.format("msg=\"Início da busca de um endereço por CEP\" method=findAddressByCep cep=%s", cep));

        var address = cepVO.generateFallbacks().stream()
                .map(findAddressByCepOutputPort::findAddressByCep)
                .filter(a -> Objects.nonNull(a) && Objects.nonNull(a.cep()) && Objects.nonNull(a.street()))
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException(String.format(Constants.ADDRESS_NOT_FOUND_MESSAGE, cep))
                );

        log.info(() -> String.format("msg=\"Fim da busca do endereço=%s por CEP\" method=findAddressByCep cep=%s", address, cep));

        return handleAddressOrAlias(cep, address);

    }

    private Address handleAddressOrAlias(String cep, Address address) {
        if (!cep.equals(address.cep())) {
            var cachedAddress = findAddressByCepOutputPort.findAddressByCep(cep);
            if (Objects.isNull(cachedAddress) || Objects.isNull(cachedAddress.cep())) {
                return findAddressByCepOutputPort.saveAddressAlias(cep, address);
            }
            return cachedAddress;
        }
        return address;
    }
}

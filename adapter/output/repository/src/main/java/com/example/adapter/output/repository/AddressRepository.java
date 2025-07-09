package com.example.adapter.output.repository;

import com.example.adapter.output.client.ViaCepClientImpl;
import com.example.adapter.output.utils.Constants;
import com.example.domain.entities.Address;
import com.example.usecase.ports.output.FindAddressByCepOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class AddressRepository implements FindAddressByCepOutputPort {
    private final ViaCepClientImpl viaCepClientImpl;

    public AddressRepository(ViaCepClientImpl viaCepClientImpl) {
        this.viaCepClientImpl = viaCepClientImpl;
    }
    @Override
    @Cacheable(value = "addressCache", key = "#cep")
    public Optional<Address> findAddressByCep(String cep) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ZIP_CODE,
                "Início da busca de um endereço por CEP", Constants.LOG_METHOD_FIND_ADDRESS, cep);

        var address = viaCepClientImpl.getAddress(cep);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ZIP_CODE,
                "Fim da busca de um endereço por CEP", Constants.LOG_METHOD_FIND_ADDRESS, cep);

        return address;
    }
}

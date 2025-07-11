package com.example.adapter.output.repository.address;

import com.example.adapter.output.client.ViaCepClientImpl;
import com.example.adapter.output.client.utils.Constants;
import com.example.domain.entities.Address;
import com.example.usecase.address.ports.output.FindAddressByCepOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class AddressRepositoryImpl implements FindAddressByCepOutputPort {

    private final ViaCepClientImpl viaCepClientImpl;

    @Override
    @Cacheable(value = "address", key = "#cep", unless = "#result == null or #result.cep == null")
    public Address findAddressByCep(String cep) {
        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_CEP,
                "Início da busca de um endereço por CEP", Constants.LOG_METHOD_FIND_ADDRESS, cep);

        var address = viaCepClientImpl.findAddressByCep(cep);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_CEP,
                "Fim da busca de um endereço por CEP", Constants.LOG_METHOD_FIND_ADDRESS, cep);

        return address;
    }

    @Override
    @CachePut(value = "address", key = "#aliasCep")
    public Address saveAddressAlias(String aliasCep, Address address) {
        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD,
                "Início da criação de um alias de busca para o cep= ", aliasCep, Constants.LOG_METHOD_ALIAS_ADDRESS);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD ,
                "Fim da criação de um alias de busca para o cep=", aliasCep, Constants.LOG_METHOD_ALIAS_ADDRESS);

        return address;
    }
}

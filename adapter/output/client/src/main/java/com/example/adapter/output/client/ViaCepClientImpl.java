package com.example.adapter.output.client;

import com.example.adapter.output.client.exception.ServiceUnavailableException;
import com.example.adapter.output.client.mapper.AddressOutputMapper;
import com.example.adapter.output.client.utils.Constants;
import com.example.domain.entities.Address;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ViaCepClientImpl {

    private final ViaCepClient viaCepClient;

    private final AddressOutputMapper mapper;

    @Retry(name = "viaCepClient")
    @CircuitBreaker(name = "viaCepClient", fallbackMethod = "fallbackFindAddress")
    public Address findAddressByCep(String cep) {
        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_CEP,
                String.format("Início da busca de um endereço no serviço VIA CEP para o CEP %s ", cep), Constants.LOG_METHOD_FIND_ADDRESS, cep);

        var address = mapper.toResponse(
             viaCepClient.findAddressByCep(cep)
        );

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_CEP,
                String.format("Fim da busca de um endereço no serviço VIA CEP para o CEP %s ", cep), Constants.LOG_METHOD_FIND_ADDRESS, cep);


        return address;
    }

    public Address fallbackFindAddress(String cep, Throwable throwable) {
        log.error(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_CEP + Constants.LOG_KEY_THROWABLE,
                String.format("Fallback executado para o CEP %s ", cep), Constants.LOG_METHOD_FALLBACK_FIND_ADDRESS, cep, throwable);

        throw new ServiceUnavailableException(String.format("O serviço VIA CEP está temporariamente indisponível. " +
                "Não foi possível buscar o endereço para o CEP %s. Tente novamente mais tarde.", cep));
    }
}

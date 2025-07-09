package com.example.adapter.output.client;

import com.example.adapter.output.exception.ServiceUnavailableException;
import com.example.domain.entities.Address;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ViaCepClientImpl {
    private final ViaCepClient viaCepClient;

    public ViaCepClientImpl(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @Retry(name = "viaCepClient")
    @CircuitBreaker(name = "viaCepClient", fallbackMethod = "fallbackGetAddress")
    public Optional<Address> getAddress(String cep) {
        return Optional.ofNullable(viaCepClient.getAddress(cep))
                .filter(r -> r.cep() != null)
                .map(r -> new Address(
                        r.cep(),
                        r.logradouro(),
                        r.bairro(),
                        r.localidade(),
                        r.uf(),
                        r.estado(),
                        r.regiao(),
                        r.ibge(),
                        r.ddd(),
                        r.siafi()
                ));
    }

    public Optional<Address> fallbackGetAddress(String cep, Throwable throwable) {
        log.error("Fallback executado para o CEP {}. Motivo: {}", cep, throwable.getMessage(), throwable);
        throw new ServiceUnavailableException(String.format("O serviço ViaCEP está temporariamente indisponível. Não foi possível buscar o endereço para o CEP %s. Tente novamente mais tarde.", cep));
    }
}

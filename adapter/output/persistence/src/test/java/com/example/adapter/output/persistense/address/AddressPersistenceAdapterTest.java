package com.example.adapter.output.persistense.address;

import com.example.adapter.output.client.ViaCepClientImpl;
import com.example.adapter.output.persistence.address.AddressPersistenceAdapter;
import com.example.domain.entities.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.adapter.output.persistense.Fixtures.ALIAS_CEP;
import static com.example.adapter.output.persistense.Fixtures.VALID_CEP;
import static com.example.adapter.output.persistense.Fixtures.validAddress;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddressPersistenceAdapterTest {

    private ViaCepClientImpl viaCepClient;
    private AddressPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        viaCepClient = mock(ViaCepClientImpl.class);
        adapter = new AddressPersistenceAdapter(viaCepClient);
    }

    @Test
    @DisplayName("Deve buscar endereço pelo CEP via client")
    void shouldFindAddressByCep() {
        Address address = validAddress();

        when(viaCepClient.findAddressByCep(VALID_CEP))
                .thenReturn(address);

        var result = adapter.findAddressByCep(VALID_CEP);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(address);

        verify(viaCepClient).findAddressByCep(VALID_CEP);
    }

    @Test
    @DisplayName("Deve retornar endereço ao salvar alias")
    void shouldSaveAddressAlias() {
        var result = adapter.saveAddressAlias(ALIAS_CEP, validAddress());

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(validAddress());
    }

    @Test
    @DisplayName("Deve propagar exceções desconhecidas do Feign client")
    void shouldPropagateOtherFeignExceptions() {
        when(viaCepClient.findAddressByCep(VALID_CEP))
                .thenThrow(new RuntimeException("Erro inesperado"));

        assertThatThrownBy(() ->
                adapter.findAddressByCep(VALID_CEP)
        ).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Erro inesperado");

        verify(viaCepClient).findAddressByCep(VALID_CEP);
    }

}

package com.example.usecase.address;

import com.example.domain.entities.Address;
import com.example.domain.utils.Constants;
import com.example.domain.vo.Cep;
import com.example.usecase.address.ports.output.FindAddressByCepOutputPort;
import com.example.usecase.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindAddressByCepUseCaseTest {

    private FindAddressByCepUseCase useCase;

    private FindAddressByCepOutputPort outputPort;

    @BeforeEach
    void setUp() {
        outputPort = mock(FindAddressByCepOutputPort.class);
        useCase = new FindAddressByCepUseCase(outputPort);
    }

    @Test
    @DisplayName("Deve retornar endereço se encontrado pelo CEP original")
    void shouldReturnAddressByOriginalCep() {
        when(outputPort.findAddressByCep(Fixtures.VALID_CEP))
                .thenReturn(Fixtures.validAddress());

        Address result = useCase.findAddressByCep(Fixtures.VALID_CEP);

        assertThat(result).isNotNull();
        assertThat(result.cep()).isEqualTo(Fixtures.VALID_CEP);
        verify(outputPort, never()).saveAddressAlias(anyString(), any(Address.class));
    }

    @Test
    @DisplayName("Deve tentar fallback quando não encontrar pelo CEP original")
    void shouldTryFallbackIfCepNotFound() {
        when(outputPort.findAddressByCep(Fixtures.VALID_CEP))
                .thenReturn(null);

        when(outputPort.findAddressByCep(anyString()))
                .thenReturn(Fixtures.validAddress());

        Address result = useCase.findAddressByCep(Fixtures.VALID_CEP);

        assertThat(result).isNotNull();
        assertThat(result.cep()).isEqualTo(Fixtures.VALID_CEP);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando nenhum endereço é encontrado")
    void shouldThrowNotFoundExceptionWhenNoAddressFound() {
        when(outputPort.findAddressByCep(anyString())).thenReturn(null);

        assertThatThrownBy(() -> useCase.findAddressByCep(Fixtures.VALID_CEP))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format(Constants.ADDRESS_NOT_FOUND_MESSAGE, Fixtures.VALID_CEP));
    }

    @Test
    @DisplayName("Deve criar alias se CEP original e CEP encontrado forem diferentes")
    void shouldCreateAliasWhenOriginalAndNormalizedCepDiffer() {
        Cep cepVO = new Cep(Fixtures.VALID_CEP);
        String fallback = cepVO.generateFallbacks().stream()
                .filter(fb -> !fb.equals(cepVO.value()))
                .findFirst()
                .orElseThrow();

        when(outputPort.findAddressByCep(cepVO.value()))
                .thenReturn(null);

        when(outputPort.findAddressByCep(fallback))
                .thenReturn(Fixtures.addressWithDifferentCep());

        Address result = useCase.findAddressByCep(Fixtures.VALID_CEP);

        assertThat(result).isNotNull();
        assertThat(result.cep()).isEqualTo(Fixtures.DIFFERENT_CEP);
        verify(outputPort).saveAddressAlias(Fixtures.VALID_CEP, Fixtures.addressWithDifferentCep());
    }

    @Test
    @DisplayName("Deve tentar fallback se endereço retornado não tiver street")
    void shouldTryFallbackWhenAddressIsInvalid() {
        when(outputPort.findAddressByCep(Fixtures.VALID_CEP))
                .thenReturn(Fixtures.invalidAddress());

        when(outputPort.findAddressByCep(anyString()))
                .thenReturn(Fixtures.validAddress());

        Address result = useCase.findAddressByCep(Fixtures.VALID_CEP);

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Não deve criar alias se o CEP original for igual ao CEP encontrado")
    void shouldNotCreateAliasWhenOriginalAndNormalizedCepAreSame() {
        Address address = Fixtures.validAddress();

        useCase.handleAddressOrAlias(
                Fixtures.VALID_CEP,
                Fixtures.VALID_CEP,
                address
        );

        verify(outputPort, never())
                .saveAddressAlias(anyString(), any(Address.class));
    }

    @Test
    @DisplayName("Deve criar alias quando CEPs forem diferentes (teste direto do método)")
    void shouldCreateAliasWhenOriginalAndNormalizedCepDiffer_Direct() {
        Address address = Fixtures.addressWithDifferentCep();

        useCase.handleAddressOrAlias(
                Fixtures.VALID_CEP,
                Fixtures.VALID_CEP,
                address
        );

        verify(outputPort)
                .saveAddressAlias(Fixtures.VALID_CEP, address);
    }


    @Test
    @DisplayName("Deve retornar false se o endereço for nulo")
    void shouldReturnFalseWhenAddressIsNull() {
        boolean result = invokeIsValidAddress(null);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Deve retornar false se o CEP do endereço for nulo")
    void shouldReturnFalseWhenCepIsNull() {
        Address address = new Address(
                null,
                Fixtures.VALID_STREET,
                Fixtures.VALID_NEIGHBORHOOD,
                Fixtures.VALID_CITY,
                Fixtures.VALID_STATE,
                Fixtures.VALID_STATE_FULL_NAME,
                Fixtures.VALID_REGION,
                Fixtures.VALID_IBGE_CODE,
                Fixtures.VALID_AREA_CODE,
                Fixtures.VALID_SIAFI_CODE
        );

        boolean result = invokeIsValidAddress(address);
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("Não deve criar alias se o CEP normalizado for igual ao CEP do endereço encontrado")
    void shouldNotCreateAliasWhenCepsAreEqual() {
        when(outputPort.findAddressByCep(Fixtures.VALID_CEP))
                .thenReturn(null);

        Address address = Fixtures.validAddress();

        when(outputPort.findAddressByCep(anyString()))
                .thenReturn(address);

        Address result = useCase.findAddressByCep(Fixtures.VALID_CEP);

        assertThat(result).isNotNull();
        verify(outputPort, never()).saveAddressAlias(anyString(), any(Address.class));
    }

    @Test
    @DisplayName("Não deve retornar endereço caso todos fallbacks sejam inválidos")
    void shouldThrowExceptionWhenAllFallbacksInvalid() {
        when(outputPort.findAddressByCep(anyString()))
                .thenReturn(null);

        assertThatThrownBy(() -> useCase.findAddressByCep(Fixtures.VALID_CEP))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Deve retornar false se o street do endereço for nulo")
    void shouldReturnFalseWhenStreetIsNull() {
        Address address = new Address(
                Fixtures.VALID_CEP,
                null,
                Fixtures.VALID_NEIGHBORHOOD,
                Fixtures.VALID_CITY,
                Fixtures.VALID_STATE,
                Fixtures.VALID_STATE_FULL_NAME,
                Fixtures.VALID_REGION,
                Fixtures.VALID_IBGE_CODE,
                Fixtures.VALID_AREA_CODE,
                Fixtures.VALID_SIAFI_CODE
        );

        boolean result = invokeIsValidAddress(address);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Não deve considerar fallback igual ao CEP original")
    void shouldIgnoreFallbackEqualToOriginalCep() {
        when(outputPort.findAddressByCep(Fixtures.VALID_CEP))
                .thenReturn(null);

        when(outputPort.findAddressByCep(anyString()))
                .thenReturn(null);

        assertThatThrownBy(() ->
                useCase.findAddressByCep(Fixtures.VALID_CEP)
        ).isInstanceOf(NotFoundException.class)
                .hasMessage(String.format(Constants.ADDRESS_NOT_FOUND_MESSAGE, Fixtures.VALID_CEP));

        verify(outputPort, never())
                .saveAddressAlias(anyString(), any(Address.class));
    }


    private boolean invokeIsValidAddress(Address address) {
        try {
            Method m = FindAddressByCepUseCase.class.getDeclaredMethod("isValidAddress", Address.class);
            m.setAccessible(true);
            return (boolean) m.invoke(useCase, address);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

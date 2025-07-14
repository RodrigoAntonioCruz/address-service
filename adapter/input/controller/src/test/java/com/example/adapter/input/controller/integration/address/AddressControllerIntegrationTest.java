package com.example.adapter.input.controller.integration.address;

import com.example.adapter.input.controller.AddressController;
import com.example.adapter.input.controller.integration.Fixtures;
import com.example.adapter.input.controller.configuration.TestsAddressConfiguration;
import com.example.adapter.input.controller.configuration.exceptionhandler.ExceptionHandlerConfiguration;
import com.example.adapter.input.controller.dto.response.AddressResponse;
import com.example.adapter.input.controller.exception.ServiceUnavailableException;
import com.example.domain.utils.Constants;
import com.example.usecase.address.ports.input.FindAddressByCepInputPort;
import com.example.usecase.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static com.example.adapter.input.controller.integration.Fixtures.FALLBACK_MESSAGE;
import static com.example.adapter.input.controller.integration.Fixtures.INVALID_CEP;
import static com.example.adapter.input.controller.integration.Fixtures.VALID_CEP;
import static com.example.domain.utils.Constants.CEP_INVALID_MESSAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {
   TestsAddressConfiguration.class, AddressController.class, ExceptionHandlerConfiguration.class },
   webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class AddressControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private FindAddressByCepInputPort findAddressByCepInputPort;

    private final HttpHeaders headers = new HttpHeaders();
    {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @DisplayName("Deve buscar endereço por CEP com sucesso")
    void shouldFindAddressByCepSuccessfully() {
        given(findAddressByCepInputPort.findAddressByCep(VALID_CEP))
                .willReturn(Fixtures.ADDRESS);

        ResponseEntity<AddressResponse> response = restTemplate.exchange(
                "/addresses/{cep}",
                HttpMethod.GET,
                new HttpEntity<Void>(headers),
                AddressResponse.class,
                VALID_CEP
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(Fixtures.ADDRESS_RESPONSE);
    }

    @Test
    @DisplayName("Deve retornar erro 400 para CEP inválido")
    void shouldReturn400ForInvalidCep() {
        given(findAddressByCepInputPort.findAddressByCep(anyString()))
                .willThrow(new IllegalArgumentException(CEP_INVALID_MESSAGE));

        ResponseEntity<Void> response = restTemplate.exchange(
                "/addresses/{cep}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Void.class,
                INVALID_CEP
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Deve retornar erro 404 quando endereço não encontrado")
    void shouldReturn404WhenAddressNotFound() {
        given(findAddressByCepInputPort.findAddressByCep(VALID_CEP))
                .willThrow(new NotFoundException(String.format(Constants.ADDRESS_NOT_FOUND_MESSAGE, VALID_CEP)));

        ResponseEntity<Void> response = restTemplate.exchange(
                "/addresses/{cep}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Void.class,
                VALID_CEP
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Deve retornar erro 503 em serviço indisponível no momento")
    void shouldReturn503OnServiceUnavailable() {
        given(findAddressByCepInputPort.findAddressByCep(VALID_CEP))
                .willThrow(new ServiceUnavailableException(FALLBACK_MESSAGE));

        ResponseEntity<Void> response = restTemplate.exchange(
                "/addresses/{cep}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Void.class,
                VALID_CEP
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
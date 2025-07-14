package com.example.adapter.output.client.integration;

import com.example.adapter.output.client.ViaCepClientImpl;
import com.example.adapter.output.client.configuration.TestConfiguration;
import com.example.adapter.output.client.exception.ServiceUnavailableException;
import com.example.domain.entities.Address;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static com.example.adapter.output.client.integration.Fixtures.CB_NAME;
import static com.example.adapter.output.client.integration.Fixtures.CIRCUIT_BREAKER_TRIGGER_ATTEMPTS;
import static com.example.adapter.output.client.integration.Fixtures.CONTENT_TYPE;
import static com.example.adapter.output.client.integration.Fixtures.CONTENT_TYPE_JSON;
import static com.example.adapter.output.client.integration.Fixtures.EXPECTED_RETRY_ATTEMPTS;
import static com.example.adapter.output.client.integration.Fixtures.HTTP_STATUS_INTERNAL_SERVER_ERROR;
import static com.example.adapter.output.client.integration.Fixtures.HTTP_STATUS_NOT_FOUND;
import static com.example.adapter.output.client.integration.Fixtures.HTTP_STATUS_OK;
import static com.example.adapter.output.client.integration.Fixtures.JSON_INVALID;
import static com.example.adapter.output.client.integration.Fixtures.JSON_PARTIAL_RESPONSE;
import static com.example.adapter.output.client.integration.Fixtures.JSON_SUCCESS_RESPONSE;
import static com.example.adapter.output.client.integration.Fixtures.RESPONSE_NEIGHBORHOOD;
import static com.example.adapter.output.client.integration.Fixtures.RESPONSE_STREET;
import static com.example.adapter.output.client.integration.Fixtures.SERVICE_UNAVAILABLE_MESSAGE;
import static com.example.adapter.output.client.integration.Fixtures.URL_PATH;
import static com.example.adapter.output.client.integration.Fixtures.VALID_CEP;
import static com.example.adapter.output.client.integration.Fixtures.VALID_CEP_FORMATTED;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest(classes = TestConfiguration.class)
class ViaCepClientImplIntegrationTest {

    @Value("${wiremock.server.host}")
    private String wireMockHost;

    @Value("${wiremock.server.port}")
    private int wireMockPort;

    private WireMockServer wireMockServer;

    @Autowired
    private ViaCepClientImpl viaCepClientImpl;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @BeforeEach
    void setUp() {
        if (Objects.isNull(wireMockServer)) {
            wireMockServer = new WireMockServer(
                    WireMockConfiguration.options().port(wireMockPort)
            );
            wireMockServer.start();
            WireMock.configureFor(wireMockHost, wireMockPort);
        }
        wireMockServer.resetAll();
    }

    @AfterEach
    void tearDown() {
        if (Objects.nonNull(wireMockServer) && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }

        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker(CB_NAME);
        if (cb != null) {
            cb.reset();
        }
    }

    @Test
    @DisplayName("Deve buscar endereço com sucesso via ViaCEP")
    void shouldFetchAddressSuccessfully() {
        stubFor(get(urlEqualTo(URL_PATH))
                .willReturn(aResponse()
                        .withStatus(HTTP_STATUS_OK)
                        .withHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
                        .withBody(JSON_SUCCESS_RESPONSE)));

        Address result = viaCepClientImpl.findAddressByCep(VALID_CEP);

        assertThat(result).isNotNull();
        assertThat(result.cep()).isEqualTo(VALID_CEP_FORMATTED);
        assertThat(result.street()).isEqualTo(RESPONSE_STREET);
        assertThat(result.neighborhood()).isEqualTo(RESPONSE_NEIGHBORHOOD);
    }

    @Test
    @DisplayName("Deve executar fallback quando ViaCEP retorna erro 500")
    void shouldExecuteFallbackOnInternalServerError() {
        stubFor(get(urlEqualTo(URL_PATH))
                .willReturn(aResponse().withStatus(HTTP_STATUS_INTERNAL_SERVER_ERROR)));

        assertThatThrownBy(() -> viaCepClientImpl.findAddressByCep(VALID_CEP))
                .isInstanceOf(ServiceUnavailableException.class)
                .hasMessageContaining(SERVICE_UNAVAILABLE_MESSAGE);
    }

    @Test
    @DisplayName("Deve executar retries antes do fallback quando ViaCEP retorna erro 500")
    void shouldRetryBeforeFallbackOnInternalServerError() {
        stubFor(get(urlEqualTo(URL_PATH))
                .willReturn(aResponse().withStatus(HTTP_STATUS_INTERNAL_SERVER_ERROR)));

        assertThatThrownBy(() -> viaCepClientImpl.findAddressByCep(VALID_CEP))
                .isInstanceOf(ServiceUnavailableException.class)
                .hasMessageContaining(SERVICE_UNAVAILABLE_MESSAGE);

        verify(EXPECTED_RETRY_ATTEMPTS, getRequestedFor(urlEqualTo(URL_PATH)));
    }

    @Test
    @DisplayName("Deve executar fallback quando ViaCEP retorna erro 404")
    void shouldExecuteFallbackOnNotFound() {
        stubFor(get(urlEqualTo(URL_PATH))
                .willReturn(aResponse().withStatus(HTTP_STATUS_NOT_FOUND)));

        assertThatThrownBy(() -> viaCepClientImpl.findAddressByCep(VALID_CEP))
                .isInstanceOf(ServiceUnavailableException.class)
                .hasMessageContaining(SERVICE_UNAVAILABLE_MESSAGE);
    }

    @Test
    @DisplayName("Deve abrir o circuit breaker após falhas consecutivas e acessar diretamente o fallback")
    void shouldOpenCircuitBreakerAfterConsecutiveFailures() {
        stubFor(get(urlEqualTo(URL_PATH))
                .willReturn(aResponse().withStatus(HTTP_STATUS_INTERNAL_SERVER_ERROR)));

        for (int i = 0; i < CIRCUIT_BREAKER_TRIGGER_ATTEMPTS; i++) {
            assertThatThrownBy(() -> viaCepClientImpl.findAddressByCep(VALID_CEP))
                    .isInstanceOf(ServiceUnavailableException.class);
        }

        wireMockServer.resetRequests();

        assertThatThrownBy(() -> viaCepClientImpl.findAddressByCep(VALID_CEP))
                .isInstanceOf(ServiceUnavailableException.class);

        verify(0, getRequestedFor(urlEqualTo(URL_PATH)));
    }

    @Test
    @DisplayName("Deve executar fallback quando ViaCEP retorna JSON inválido")
    void shouldExecuteFallbackOnInvalidJson() {
        stubFor(get(urlEqualTo(URL_PATH))
                .willReturn(aResponse()
                        .withStatus(HTTP_STATUS_OK)
                        .withHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
                        .withBody(JSON_INVALID)));

        assertThatThrownBy(() -> viaCepClientImpl.findAddressByCep(VALID_CEP))
                .isInstanceOf(ServiceUnavailableException.class)
                .hasMessageContaining(SERVICE_UNAVAILABLE_MESSAGE);
    }

    @Test
    @DisplayName("Deve processar JSON incompleto sem lançar exceção")
    void shouldProcessPartialJsonWithoutException() {
        stubFor(get(urlEqualTo(URL_PATH))
                .willReturn(aResponse()
                        .withStatus(HTTP_STATUS_OK)
                        .withHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
                        .withBody(JSON_PARTIAL_RESPONSE)));

        Address result = viaCepClientImpl.findAddressByCep(VALID_CEP);

        assertThat(result).isNotNull();
        assertThat(result.cep()).isEqualTo(VALID_CEP_FORMATTED);
        assertThat(result.street()).isNull();
        assertThat(result.neighborhood()).isNull();
    }
}

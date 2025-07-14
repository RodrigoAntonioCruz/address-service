package com.example.adapter.input.controller.integration.user;

import com.example.adapter.input.controller.UserSignUpController;
import com.example.adapter.input.controller.configuration.TestsUserConfiguration;
import com.example.adapter.input.controller.dto.response.SignUpResponse;
import com.example.adapter.input.controller.integration.Fixtures;
import com.example.domain.entities.User;
import com.example.usecase.user.ports.input.SignUpInputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(
    classes = { TestsUserConfiguration.class, UserSignUpController.class },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class UserSignUpControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private SignUpInputPort signUpInputPort;

    private final HttpHeaders headers = new HttpHeaders();
    {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @DisplayName("Deve criar usuário e retornar 201")
    void shouldCreateUserAndReturn201() {
        given(signUpInputPort.signUp(ArgumentMatchers.any(User.class)))
                .willReturn(Fixtures.USER);

        var request = new HttpEntity<>(Fixtures.VALID_SIGNUP_JSON, headers);

        ResponseEntity<SignUpResponse> response = restTemplate.postForEntity(
                "/auth/signup", request, SignUpResponse.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(Fixtures.SIGNUP_RESPONSE_JSON);
    }

    @Test
    @DisplayName("Deve retornar 409 quando username já existe")
    void shouldReturn409WhenUsernameExists() {
        given(signUpInputPort.signUp(ArgumentMatchers.any(User.class)))
                .willThrow(new ResponseStatusException(HttpStatus.CONFLICT, "username exists"));

        var request = new HttpEntity<>(Fixtures.VALID_SIGNUP_JSON, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                "/auth/signup",
                HttpMethod.POST,
                request,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("Deve retornar 409 quando email já existe")
    void shouldReturn409WhenEmailExists() {
        given(signUpInputPort.signUp(ArgumentMatchers.any(User.class)))
                .willThrow(new ResponseStatusException(HttpStatus.CONFLICT, "email exists"));

        var request = new HttpEntity<>(Fixtures.VALID_SIGNUP_JSON, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                "/auth/signup",
                HttpMethod.POST,
                request,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("Deve retornar 400 para payload de signup inválido")
    void shouldReturn400ForInvalidPayload() {
        var request = new HttpEntity<>(Fixtures.INVALID_SIGNUP_JSON, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                "/auth/signup",
                HttpMethod.POST,
                request,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

package com.example.usecase.user;

import com.example.domain.entities.User;
import com.example.domain.utils.Constants;
import com.example.usecase.exception.DuplicatedException;
import com.example.usecase.user.ports.output.SignUpOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupUserUseCaseTest {

    private SignupUserUseCase useCase;

    private SignUpOutputPort outputPort;

    @BeforeEach
    void setUp() {
        outputPort = mock(SignUpOutputPort.class);
        useCase = new SignupUserUseCase(outputPort);
    }

    @Test
    @DisplayName("Deve criar usuário se username e email forem únicos")
    void shouldSignUpUserWhenDataIsUnique() {
        User user = Fixtures.validUser();

        when(outputPort.findByUsername(Fixtures.VALID_USERNAME))
                .thenReturn(Optional.empty());
        when(outputPort.findByEmail(Fixtures.VALID_EMAIL))
                .thenReturn(Optional.empty());
        when(outputPort.signUp(user))
                .thenReturn(user);

        User result = useCase.signUp(user);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(Fixtures.VALID_USERNAME);
        verify(outputPort).signUp(user);
    }

    @Test
    @DisplayName("Deve lançar exceção se username já existir")
    void shouldThrowExceptionWhenUsernameAlreadyExists() {
        User user = Fixtures.validUser();

        when(outputPort.findByUsername(Fixtures.VALID_USERNAME))
                .thenReturn(Optional.of(user));

        assertThatThrownBy(() -> useCase.signUp(user))
                .isInstanceOf(DuplicatedException.class)
                .hasMessage(String.format(Constants.USERNAME_ALREADY_EXISTS, Fixtures.VALID_USERNAME));

        verify(outputPort, never()).signUp(any(User.class));
    }

    @Test
    @DisplayName("Deve lançar exceção se email já existir")
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        User user = Fixtures.validUser();

        when(outputPort.findByUsername(Fixtures.VALID_USERNAME))
                .thenReturn(Optional.empty());
        when(outputPort.findByEmail(Fixtures.VALID_EMAIL))
                .thenReturn(Optional.of(user));

        assertThatThrownBy(() -> useCase.signUp(user))
                .isInstanceOf(DuplicatedException.class)
                .hasMessage(String.format(Constants.EMAIL_ALREADY_EXISTS, Fixtures.VALID_EMAIL));

        verify(outputPort, never()).signUp(any(User.class));
    }
}

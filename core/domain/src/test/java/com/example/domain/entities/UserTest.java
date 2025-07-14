package com.example.domain.entities;

import com.example.domain.utils.Constants;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Testes Unitários da Entidade User")
class UserTest {

    @Test
    @DisplayName("Deve criar User com sucesso quando todos os campos são válidos")
    void shouldCreateUserSuccessfully() {
        User user = new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        );

        assertThat(user.getId()).isEqualTo(Fixtures.VALID_ID);
        assertThat(user.getUsername()).isEqualTo(Fixtures.VALID_USERNAME);
        assertThat(user.getPassword()).isEqualTo(Fixtures.VALID_PASSWORD);
        assertThat(user.getName()).isEqualTo(Fixtures.VALID_NAME);
        assertThat(user.getCpf()).isEqualTo(Fixtures.VALID_CPF);
        assertThat(user.getEmail()).isEqualTo(Fixtures.VALID_EMAIL);
        assertThat(user.getRole()).isEqualTo(Fixtures.VALID_ROLE);
    }

    @Test
    @DisplayName("Deve lançar exceção se o username for nulo")
    void shouldThrowExceptionWhenUsernameIsNull() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                null,
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.USERNAME_REQUIRED);
    }

    @Test
    @DisplayName("Deve lançar exceção se o username estiver em branco")
    void shouldThrowExceptionWhenUsernameIsBlank() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                "   ",
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.USERNAME_REQUIRED);
    }

    @Test
    @DisplayName("Deve lançar exceção se o nome for nulo")
    void shouldThrowExceptionWhenNameIsNull() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                Fixtures.VALID_PASSWORD,
                null,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.NAME_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção se a senha for nula")
    void shouldThrowExceptionWhenPasswordIsNull() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                null,
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.PASSWORD_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção se o CPF for nulo")
    void shouldThrowExceptionWhenCpfIsNull() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                null,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.CPF_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção se o e-mail for nulo")
    void shouldThrowExceptionWhenEmailIsNull() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                null,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.EMAIL_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção se o Role for nulo")
    void shouldThrowExceptionWhenRoleIsNull() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                null
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.ROLE_NOT_NULL);
    }

    @Test
    @DisplayName("Deve permitir alteração de campos do User através dos setters")
    void shouldChangeFieldsUsingSetters() {
        User user = getUser();

        assertThat(user.getId()).isEqualTo(Fixtures.VALID_ID_UPDATED);
        assertThat(user.getUsername()).isEqualTo(Fixtures.VALID_USERNAME_UPDATED);
        assertThat(user.getPassword().getValue()).isEqualTo(Fixtures.VALID_PASSWORD_UPDATED_STR);
        assertThat(user.getName()).isEqualTo(Fixtures.VALID_NAME_UPDATED);
        assertThat(user.getCpf().getValue()).isEqualTo(Fixtures.VALID_CPF_UPDATED_STR);
        assertThat(user.getEmail().getValue()).isEqualTo(Fixtures.VALID_EMAIL_UPDATED_STR);
        assertThat(user.getRole()).isEqualTo(Fixtures.VALID_ROLE_UPDATED);
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar User com CPF inválido")
    void shouldThrowExceptionWhenCpfIsInvalid() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                new Cpf(Fixtures.INVALID_CPF_STR),
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.CEP_INVALID_MESSAGE);
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar User com e-mail inválido")
    void shouldThrowExceptionWhenEmailIsInvalid() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                new Email(Fixtures.INVALID_EMAIL_STR),
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.EMAIL_INVALID);
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar User com senha fora do padrão exigido")
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                new Password(Fixtures.INVALID_PASSWORD_STR),
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A senha deve conter pelo menos uma letra, um número e um caractere especial");
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar User com senha menor que 8 caracteres")
    void shouldThrowExceptionWhenPasswordIsTooShort() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                new Password(Fixtures.INVALID_PASSWORD_SHORT_STR),
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.PASSWORD_MIN_LENGTH);
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar User com senha contendo caracteres inválidos")
    void shouldThrowExceptionWhenPasswordContainsInvalidCharacters() {
        assertThatThrownBy(() -> new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                new Password(Fixtures.INVALID_PASSWORD_INVALID_CHAR_STR),
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.PASSWORD_COMPLEXITY);
    }

    private static User getUser() {
        User user = new User(
                Fixtures.VALID_ID,
                Fixtures.VALID_USERNAME,
                Fixtures.VALID_PASSWORD,
                Fixtures.VALID_NAME,
                Fixtures.VALID_CPF,
                Fixtures.VALID_EMAIL,
                Fixtures.VALID_ROLE
        );

        user.setId(Fixtures.VALID_ID_UPDATED);
        user.setUsername(Fixtures.VALID_USERNAME_UPDATED);
        user.setPassword(Fixtures.VALID_PASSWORD_UPDATED);
        user.setName(Fixtures.VALID_NAME_UPDATED);
        user.setCpf(Fixtures.VALID_CPF_UPDATED);
        user.setEmail(Fixtures.VALID_EMAIL_UPDATED);
        user.setRole(Fixtures.VALID_ROLE_UPDATED);
        return user;
    }
}

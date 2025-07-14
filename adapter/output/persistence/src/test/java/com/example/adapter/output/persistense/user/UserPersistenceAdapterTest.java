package com.example.adapter.output.persistense.user;

import com.example.adapter.output.persistence.user.UserPersistenceAdapter;
import com.example.adapter.output.persistence.user.mapper.UserOutputMapper;
import com.example.adapter.output.persistence.user.repository.UserEntityRepository;
import com.example.adapter.output.persistense.Fixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserPersistenceAdapterTest {

    private UserOutputMapper mapper;
    private PasswordEncoder encoder;
    private UserEntityRepository repository;
    private UserPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        mapper = mock(UserOutputMapper.class);
        encoder = mock(PasswordEncoder.class);
        repository = mock(UserEntityRepository.class);

        adapter = new UserPersistenceAdapter(mapper, encoder, repository);
    }

    @Test
    @DisplayName("Deve salvar usuário com senha encriptada e retornar domínio")
    void shouldSignUpUser() {
        var user = Fixtures.validUser();
        var userEntity = Fixtures.validUserEntity();

        when(mapper.toEntity(user, encoder))
                .thenReturn(userEntity);
        when(repository.save(userEntity))
                .thenReturn(userEntity);
        when(mapper.toDomain(userEntity))
                .thenReturn(user);

        var result = adapter.signUp(user);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(Fixtures.VALID_USERNAME);
        verify(mapper).toEntity(user, encoder);
        verify(repository).save(userEntity);
        verify(mapper).toDomain(userEntity);
    }

    @Test
    @DisplayName("Deve retornar usuário ao buscar por email existente")
    void shouldFindUserByEmail() {
        var userEntity = Fixtures.validUserEntity();
        var user = Fixtures.validUser();

        when(repository.findByEmail(Fixtures.VALID_EMAIL))
                .thenReturn(Optional.of(userEntity));
        when(mapper.toDomain(userEntity))
                .thenReturn(user);

        var result = adapter.findByEmail(Fixtures.VALID_EMAIL);

        assertThat(result).isPresent();
        assertThat(result.get().getEmail().getValue()).isEqualTo(Fixtures.VALID_EMAIL);
    }

    @Test
    @DisplayName("Deve retornar vazio ao buscar email inexistente")
    void shouldReturnEmptyWhenEmailNotFound() {
        when(repository.findByEmail(Fixtures.VALID_EMAIL))
                .thenReturn(Optional.empty());

        var result = adapter.findByEmail(Fixtures.VALID_EMAIL);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar usuário ao buscar por username existente")
    void shouldFindUserByUsername() {
        var userEntity = Fixtures.validUserEntity();
        var user = Fixtures.validUser();

        when(repository.findByUsername(Fixtures.VALID_USERNAME))
                .thenReturn(Optional.of(userEntity));
        when(mapper.toDomain(userEntity))
                .thenReturn(user);

        var result = adapter.findByUsername(Fixtures.VALID_USERNAME);

        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo(Fixtures.VALID_USERNAME);
    }

    @Test
    @DisplayName("Deve retornar vazio ao buscar username inexistente")
    void shouldReturnEmptyWhenUsernameNotFound() {
        when(repository.findByUsername(Fixtures.VALID_USERNAME))
                .thenReturn(Optional.empty());

        var result = adapter.findByUsername(Fixtures.VALID_USERNAME);

        assertThat(result).isEmpty();
    }
}

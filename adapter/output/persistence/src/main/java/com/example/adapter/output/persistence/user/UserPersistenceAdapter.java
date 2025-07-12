package com.example.adapter.output.persistence.user;


import com.example.adapter.output.client.utils.Constants;
import com.example.adapter.output.persistence.user.mapper.UserOutputMapper;
import com.example.adapter.output.persistence.user.repository.UserEntityRepository;
import com.example.domain.entities.User;
import com.example.usecase.user.ports.output.SignUpOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class UserPersistenceAdapter implements SignUpOutputPort {

    private final UserOutputMapper mapper;

    private final PasswordEncoder encoder;

    private final UserEntityRepository repository;

    @Override
    public User signUp(User user) {
        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Início da persistência de um usuário ", Constants.LOG_METHOD_SIGNUP, user);

        var entity = mapper.toEntity(user, encoder);

        entity = repository.save(entity);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim da persistência de um usuário ", Constants.LOG_METHOD_SIGNUP, entity);

        return mapper.toDomain(entity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }
}



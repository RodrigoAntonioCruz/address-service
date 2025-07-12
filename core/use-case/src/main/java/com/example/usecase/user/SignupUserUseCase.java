package com.example.usecase.user;

import com.example.domain.entities.User;
import com.example.domain.utils.Constants;
import com.example.usecase.exception.DuplicatedException;
import com.example.usecase.user.ports.input.SignUpInputPort;
import com.example.usecase.user.ports.output.SignUpOutputPort;

import java.util.logging.Logger;

public class SignupUserUseCase implements SignUpInputPort {

    private static final Logger LOG = Logger.getLogger(SignupUserUseCase.class.getName());

    private final SignUpOutputPort signUpOutputPort;

    public SignupUserUseCase(SignUpOutputPort signUpOutputPort) {
        this.signUpOutputPort = signUpOutputPort;
    }

    @Override
    public User signUp(User user) {
        LOG.info(() -> String.format("msg=\"Início da criação de um usuário\" method=signUp entity=%s", user.getUsername()));

        validateUsernameUniqueness(user);
        validateEmailUniqueness(user);

        User saved = signUpOutputPort.signUp(user);

        LOG.info(() -> String.format("msg=\"Fim da criação de um usuário\" method=signUp entity=%s", user.getUsername()));

        return saved;
    }

    private void validateUsernameUniqueness(User user) {
        signUpOutputPort.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new DuplicatedException(
                            String.format(Constants.USERNAME_ALREADY_EXISTS, user.getUsername())
                    );
                });
    }

    private void validateEmailUniqueness(User user) {
        signUpOutputPort.findByEmail(user.getEmail().getValue())
                .ifPresent(u -> {
                    throw new DuplicatedException(
                            String.format(Constants.EMAIL_ALREADY_EXISTS, user.getEmail().getValue())
                    );
                });
    }
}
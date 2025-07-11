package com.example.usecase.user;

import com.example.domain.entities.User;
import com.example.domain.utils.Constants;
import com.example.usecase.exception.DuplicatedException;
import com.example.usecase.user.ports.input.SignUpInputPort;
import com.example.usecase.user.ports.output.SignUpOutputPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Logger;
@Named
@ApplicationScoped
public class SignupUserUseCase implements SignUpInputPort {

    private static final Logger log = Logger.getLogger(SignupUserUseCase.class.getName());

    private final SignUpOutputPort signUpOutputPort;

    @Inject
    public SignupUserUseCase(SignUpOutputPort signUpOutputPort) {
        this.signUpOutputPort = signUpOutputPort;
    }

    @Override
    public User signUp(User user) {
        log.info("Iniciando cadastro de usuário: " + user.getUsername());

        log.info(() -> String.format("msg=\"Início da criação de um usuário \" method=signUp entity=%s", user.getUsername()));

        signUpOutputPort.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new DuplicatedException(
                            String.format(Constants.USERNAME_ALREADY_EXISTS, user.getUsername())
                    );
                });

        signUpOutputPort.findByEmail(user.getEmail().getValue())
                .ifPresent(u -> {
                    throw new DuplicatedException(
                            String.format(Constants.EMAIL_ALREADY_EXISTS, user.getEmail().getValue())
                    );
                });

        User saved = signUpOutputPort.signUp(user);

        log.info(() -> String.format("msg=\"Fim da criação de um usuário \" method=signUp entity=%s", user.getUsername()));

        return saved;
    }
}

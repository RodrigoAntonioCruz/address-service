package com.example.usecase.user.ports.output;

import com.example.domain.entities.User;

import java.util.Optional;

public interface SignUpOutputPort {
    User signUp(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}

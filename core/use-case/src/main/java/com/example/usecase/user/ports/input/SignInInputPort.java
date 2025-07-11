package com.example.usecase.user.ports.input;

import com.example.domain.entities.User;

public interface SignInInputPort {
    User signIn(String email, String password);
}

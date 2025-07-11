package com.example.usecase.user.ports.output;

import com.example.domain.entities.User;

public interface SignInOutputPort {
    User signIn(String email, String password);
}

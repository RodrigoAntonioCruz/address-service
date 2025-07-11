package com.example.usecase.user.ports.input;

import com.example.domain.entities.User;

public interface SignUpInputPort {
    void signUp(User user);
}

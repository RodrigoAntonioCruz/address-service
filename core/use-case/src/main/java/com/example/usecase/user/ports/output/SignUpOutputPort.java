package com.example.usecase.user.ports.output;

import com.example.domain.entities.User;

public interface SignUpOutputPort {
    User signUp(User user);
}

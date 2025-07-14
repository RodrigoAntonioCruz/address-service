package com.example.adapter.output.security;

import com.example.adapter.output.security.dto.CustomUserDetails;
import com.example.domain.entities.User;
import com.example.domain.enums.Role;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;

public class Fixtures {

    public static final String VALID_USERNAME = "user123";
    public static final String VALID_PASSWORD = "secret123!";
    public static final String VALID_FULL_NAME = "Rodrigo Antonio";
    public static final String VALID_CPF = "12345678909";
    public static final String VALID_EMAIL = "rodrigo@email.com";
    public static final String VALID_ID = "1";

    public static User validUser() {
        return new User(
                VALID_ID,
                VALID_USERNAME,
                new Password(VALID_PASSWORD),
                VALID_FULL_NAME,
                new Cpf(VALID_CPF),
                new Email(VALID_EMAIL),
                Role.USER
        );
    }

    public static CustomUserDetails customUserDetails() {
        User user = validUser();
        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword().getValue(),
                user.getRole().name()
        );
    }
}

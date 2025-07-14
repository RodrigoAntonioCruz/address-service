package com.example.usecase.user;

import com.example.domain.entities.User;
import com.example.domain.enums.Role;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;

public class Fixtures {

    public static final String VALID_ID = "123";
    public static final String VALID_USERNAME = "user123";
    public static final String VALID_PASSWORD = "secret123!";
    public static final String VALID_FULLNAME = "Rodrigo Antonio";
    public static final String VALID_CPF = "12345678909";
    public static final String VALID_EMAIL = "rodrigo@email.com";

    public static User validUser() {
        return new User(
                VALID_ID,
                VALID_USERNAME,
                new Password(VALID_PASSWORD),
                VALID_FULLNAME,
                new Cpf(VALID_CPF),
                new Email(VALID_EMAIL),
                Role.USER
        );
    }
}

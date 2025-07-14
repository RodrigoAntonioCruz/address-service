package com.example.domain.entities;

import com.example.domain.enums.Role;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;

public final class Fixtures {

    private Fixtures() {
    }

    public static final String INVALID_PASSWORD_SHORT_STR = "Ab1!";
    public static final String INVALID_PASSWORD_INVALID_CHAR_STR = "secretç123!";
    public static final String INVALID_CPF_STR = "123";
    public static final String INVALID_EMAIL_STR = "invalido-email";
    public static final String INVALID_PASSWORD_STR = "senha123";
    public static final String VALID_ID = "123";
    public static final String VALID_ID_UPDATED = "456";
    public static final String VALID_USERNAME = "user123";
    public static final String VALID_USERNAME_UPDATED = "newuser";
    public static final String VALID_NAME = "Rodrigo Antonio";
    public static final String VALID_NAME_UPDATED = "Jane Carla";
    public static final String VALID_PASSWORD_STR = "secret123!";
    public static final String VALID_PASSWORD_UPDATED_STR = "newSecret1!";
    public static final Password VALID_PASSWORD = new Password(VALID_PASSWORD_STR);
    public static final Password VALID_PASSWORD_UPDATED = new Password(VALID_PASSWORD_UPDATED_STR);
    public static final String VALID_CPF_STR = "12345678909";
    public static final String VALID_CPF_UPDATED_STR = "98765432100";
    public static final Cpf VALID_CPF = new Cpf(VALID_CPF_STR);
    public static final Cpf VALID_CPF_UPDATED = new Cpf(VALID_CPF_UPDATED_STR);
    public static final String VALID_EMAIL_STR = "rodrigo123@email.com";
    public static final String VALID_EMAIL_UPDATED_STR = "rodrigo1235@email.com";
    public static final Email VALID_EMAIL = new Email(VALID_EMAIL_STR);
    public static final Email VALID_EMAIL_UPDATED = new Email(VALID_EMAIL_UPDATED_STR);
    public static final Role VALID_ROLE = Role.USER;
    public static final Role VALID_ROLE_UPDATED = Role.ADMIN;
    public static final String VALID_CEP = "01001000";
    public static final String VALID_STREET = "Praça da Sé";
    public static final String VALID_NEIGHBORHOOD = "Sé";
    public static final String VALID_CITY = "São Paulo";
    public static final String VALID_STATE = "SP";
    public static final String VALID_STATE_FULL_NAME = "São Paulo";
    public static final String VALID_REGION = "Sudeste";
    public static final String VALID_IBGE_CODE = "3550308";
    public static final String VALID_AREA_CODE = "11";
    public static final String VALID_SIAFI_CODE = "7107";

    public static Address validAddress() {
        return new Address(
                VALID_CEP,
                VALID_STREET,
                VALID_NEIGHBORHOOD,
                VALID_CITY,
                VALID_STATE,
                VALID_STATE_FULL_NAME,
                VALID_REGION,
                VALID_IBGE_CODE,
                VALID_AREA_CODE,
                VALID_SIAFI_CODE
        );
    }
}

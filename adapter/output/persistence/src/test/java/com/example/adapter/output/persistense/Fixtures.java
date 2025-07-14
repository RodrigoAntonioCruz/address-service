package com.example.adapter.output.persistense;

import com.example.adapter.output.persistence.user.entity.UserEntity;
import com.example.domain.entities.Address;
import com.example.domain.entities.User;
import com.example.domain.vo.Password;

public class Fixtures {

    public static final String VALID_ID = "123";
    public static final String VALID_USERNAME = "user123";
    public static final String VALID_PASSWORD = "secret123!";
    public static final String VALID_EMAIL = "rodrigo@email.com";
    public static final String VALID_FULL_NAME = "Rodrigo da Cruz";
    public static final String VALID_CPF = "73786275009";
    public static final String VALID_ROLE = "ADMIN";
    public static final String VALID_CEP = "01001000";
    public static final String ALIAS_CEP = "01001001";
    public static final String VALID_STREET = "Praça da Sé";
    public static final String VALID_NEIGHBORHOOD = "Sé";
    public static final String VALID_CITY = "São Paulo";
    public static final String VALID_STATE = "SP";
    public static final String VALID_STATE_FULL_NAME = "São Paulo";
    public static final String VALID_REGION = "Sudeste";
    public static final String VALID_IBGE_CODE = "3550308";
    public static final String VALID_AREA_CODE = "11";
    public static final String VALID_SIAFI_CODE = "7107";

    public static User validUser() {
        return new User(
                VALID_ID,
                VALID_USERNAME,
                new Password(VALID_PASSWORD),
                VALID_FULL_NAME,
                new com.example.domain.vo.Cpf(VALID_CPF),
                new com.example.domain.vo.Email(VALID_EMAIL),
                com.example.domain.enums.Role.USER
        );
    }

    public static UserEntity validUserEntity() {
        return UserEntity.builder()
                .id(VALID_ID)
                .username(VALID_USERNAME)
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .name(VALID_FULL_NAME)
                .cpf(VALID_CPF)
                .role(VALID_ROLE)
                .build();
    }

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

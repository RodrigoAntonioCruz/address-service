package com.example.adapter.input.controller.integration;

import com.example.adapter.input.controller.dto.response.AddressResponse;
import com.example.adapter.input.controller.dto.response.SignUpResponse;
import com.example.domain.entities.Address;
import com.example.domain.entities.User;
import com.example.domain.enums.Role;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;

import java.util.UUID;

public final class Fixtures {

    private Fixtures() {
    }

    public static final String ID = UUID.fromString("00000000-0000-0000-0000-000000000001").toString();
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
    public static final String INVALID_CEP = "11ssssss@@@@@@@$$";
    public static final String FALLBACK_MESSAGE = "O serviço está temporariamente indisponível. Tente novamente mais tarde.";

    public static final String VALID_SIGNUP_JSON = """
        {
            "username": "rodrigo",
            "password": "P@ssw0rd!",
            "name": "Rodrigo da Cruz",
            "cpf": "73786275009",
            "email": "rodrigo@gmail.com",
            "role": "USER"
        }
    """;

    public static final String INVALID_SIGNUP_JSON = """
        {
            "username": "",
            "password": "",
            "name": "",
            "cpf": "invalid-cpf",
            "email": "invalid-email",
            "role": ""
        }
    """;

    public static final SignUpResponse SIGNUP_RESPONSE_JSON = new SignUpResponse(
            ID,
            "rodrigo",
            "Rodrigo da Cruz",
            "rodrigo@gmail.com",
            "73786275009",
            "USER"
    );

    public static final User USER = new User(
            ID,
            "rodrigo",
            new Password("P@ssw0rd!"),
            "Rodrigo da Cruz",
            new Cpf("73786275009"),
            new Email("rodrigo@gmail.com"),
            Role.USER
    );


    public static final Address ADDRESS =  new Address(
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


    public static final AddressResponse ADDRESS_RESPONSE = new AddressResponse(
            VALID_CEP,
            VALID_STREET,
            VALID_NEIGHBORHOOD,
            VALID_CITY,
            VALID_STATE
    );
}

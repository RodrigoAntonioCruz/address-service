package com.example.usecase.address;

import com.example.domain.entities.Address;

public final class Fixtures {

    private Fixtures() {}

    public static final String VALID_CEP = "01001000";
    public static final String DIFFERENT_CEP = "01001001";
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

    public static Address addressWithDifferentCep() {
        return new Address(
                DIFFERENT_CEP,
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

    public static Address invalidAddress() {
        return new Address(
                VALID_CEP,
                null,
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

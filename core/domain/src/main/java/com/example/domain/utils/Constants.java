package com.example.domain.utils;


public class Constants {
    private Constants() {
    }
    public  static final String CEP_REGEX = "\\d{8}";
    public  static final char ZERO_CHAR = '0';
    public static final String CEP_NOT_FOUND_VALUE = "00000000";
    public static final String CEP_INVALID_MESSAGE = "CEP informado é inválido";
    public static final String ID_NOT_NULL = "O campo id não pode ser nulo";
    public static final String VALUE_NOT_NULL = "O campo valor não pode ser nulo";
    public static final String DESCRIPTION_NOT_NULL = "O campo descrição não pode ser nulo";
}

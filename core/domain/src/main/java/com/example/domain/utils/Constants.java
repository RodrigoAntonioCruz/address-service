package com.example.domain.utils;


public class Constants {

    private Constants() {
    }
    public static final String BCRYPT_PREFIX = "{bcrypt}";
    public static final String BCRYPT_PREFIX_2A = "$2a$";
    public static final String BCRYPT_PREFIX_2B = "$2b$";
    public static final String BCRYPT_PREFIX_2Y = "$2y$";
    public static final int CPF_LENGTH = 11;
    public static final int FIRST_DIGITS_LENGTH = 9;
    public static final int INVALID_CPF_THRESHOLD = 9;
    public static final int MIN_DIGIT_ZERO = 0;
    public static final int WEIGHT_11 = 11;
    public static final int WEIGHT_10 = 10;
    public static final int WEIGHT_9 = 9;
    public static final int WEIGHT_8 = 8;
    public static final int WEIGHT_7 = 7;
    public static final int WEIGHT_6 = 6;
    public static final int WEIGHT_5 = 5;
    public static final int WEIGHT_4 = 4;
    public static final int WEIGHT_3 = 3;
    public static final int WEIGHT_2 = 2;
    public static final String DIGIT_REGEX = "\\D";
    public  static final String CEP_REGEX = "\\d{8}";
    public  static final char ZERO_CHAR = '0';
    public static final String EMPTY = "";
    public static final int MINIMUM_LENGTH = 8;
    public static final String CEP_NOT_FOUND_VALUE = "00000000";
    public static final String MASKED_PASSWORD = "********";
    public static final String PASSWORD_NULL_OR_BLANK = "A senha não pode ser nula ou estar em branco";
    public static final String PASSWORD_MIN_LENGTH = "A senha deve ter pelo menos 8 caracteres";
    public static final String PASSWORD_COMPLEXITY = "A senha deve conter pelo menos uma letra, um número e um caractere especial";
    public static final String ADDRESS_NOT_FOUND_MESSAGE = "O endereço para o cep: %s não foi encontrado";
    public static final String PASSWORD_COMPLEXITY_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$";
    public static final String CEP_INVALID_MESSAGE = "O CEP informado é inválido";
    public static final String CPF_NOT_NULL = "O CPF não pode ser nulo";
    public static final String EMAIL_INVALID = "O e-mail informado é inválido";
    public static final String EMAIL_NOT_NULL = "O e-mail não pode ser nulo ou vazio";
    public static final String NAME_NOT_NULL = "O nome não pode ser nulo ou vazio";
    public static final String USERNAME_REQUIRED = "Username não pode ser nulo ou vazio";
    public static final String PASSWORD_NOT_NULL = "Senha não pode ser nula ou vazia";
    public static final String ROLE_NOT_NULL = "O perfil não pode ser nulo ou vazio";
    public static final String USERNAME_ALREADY_EXISTS = "O username: %s já existe";
    public static final String EMAIL_ALREADY_EXISTS = "O e-mail: %s já existe";
}

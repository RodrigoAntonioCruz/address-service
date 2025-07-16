package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.Objects;

public class Cpf {
    private final String value;
    private static final int[] WEIGHT_SSN = {
            Constants.WEIGHT_11,
            Constants.WEIGHT_10,
            Constants.WEIGHT_9,
            Constants.WEIGHT_8,
            Constants.WEIGHT_7,
            Constants.WEIGHT_6,
            Constants.WEIGHT_5,
            Constants.WEIGHT_4,
            Constants.WEIGHT_3,
            Constants.WEIGHT_2
    };

    public Cpf(final String value) {
        Objects.requireNonNull(value, Constants.CPF_NOT_NULL);

        final String normalized = value.replaceAll(Constants.DIGIT_REGEX, Constants.EMPTY);
        if (normalized.length() != Constants.CPF_LENGTH || !isValid(normalized)) {
            throw new IllegalArgumentException(Constants.CEP_INVALID_MESSAGE);
        }
        this.value = normalized;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cpf cpf)) {
            return false;
        }
        return Objects.equals(value, cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private static int calculate(final String str) {
        int sum = 0;
        int digit;
        for (int i = str.length() - 1; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * WEIGHT_SSN[WEIGHT_SSN.length - str.length() + i];
        }
        sum = Constants.CPF_LENGTH - sum % Constants.CPF_LENGTH;
        return sum > Constants.INVALID_CPF_THRESHOLD ? Constants.MIN_DIGIT_ZERO : sum;
    }

    private static boolean isValid(final String ssn) {
        if (ssn == null || ssn.length() != Constants.CPF_LENGTH || ssn.matches(ssn.charAt(0) + "{" + Constants.CPF_LENGTH + "}")) {
            return false;
        }

        final int digit1 = calculate(ssn.substring(Constants.MIN_DIGIT_ZERO, Constants.FIRST_DIGITS_LENGTH));
        final int digit2 = calculate(ssn.substring(Constants.MIN_DIGIT_ZERO, Constants.FIRST_DIGITS_LENGTH) + digit1);
        return ssn.equals(ssn.substring(Constants.MIN_DIGIT_ZERO, Constants.FIRST_DIGITS_LENGTH) + digit1 + digit2);
    }
}

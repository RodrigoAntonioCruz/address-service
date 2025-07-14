package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.Objects;

public class Cpf {
    private final String value;

    private static final int[] WEIGHT_SSN = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    public Cpf(String value) {
        Objects.requireNonNull(value, Constants.CPF_NOT_NULL);

        String normalized = value.replaceAll("\\D", "");
        if (normalized.length() != 11 || !isValid(normalized)) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cpf cpf)) return false;
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
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    private static boolean isValid(final String ssn) {
        if ((ssn == null) || (ssn.length() != 11) || ssn.matches(ssn.charAt(0) + "{11}")) return false;

        var digit1 = calculate(ssn.substring(0, 9));
        var digit2 = calculate(ssn.substring(0, 9) + digit1);
        return ssn.equals(ssn.substring(0, 9) + digit1 + digit2);
    }
}

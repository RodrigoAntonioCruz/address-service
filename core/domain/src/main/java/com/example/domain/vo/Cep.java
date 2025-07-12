package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public record Cep(String value) {

    public Cep(String value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException(Constants.CEP_INVALID_MESSAGE);
        }

        String digitsOnly = value.replaceAll(Constants.DIGIT_REGEX, Constants.EMPTY);

        if (!digitsOnly.matches(Constants.CEP_REGEX)) {
            throw new IllegalArgumentException(Constants.CEP_INVALID_MESSAGE);
        }

        this.value = digitsOnly;
    }

    public List<String> generateFallbacks() {
        return Stream.iterate(
                value,
                prev -> !prev.equals(Constants.CEP_NOT_FOUND_VALUE),
                this::zeroRightmostDigit
        ).toList();
    }

    private String zeroRightmostDigit(String cep) {
        char[] digits = cep.toCharArray();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != Constants.ZERO_CHAR) {
                digits[i] = Constants.ZERO_CHAR;
                break;
            }
        }
        return new String(digits);
    }

    public String getFormatted() {
        return value.substring(0, 5) + "-" + value.substring(5);
    }

    @Override
    public String toString() {
        return value;
    }
}

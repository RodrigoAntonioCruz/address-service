package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public record Cep(String value) {

    public Cep(String value) {
        String trimmed = Objects.isNull(value) ? null : value.trim();
        if (Objects.isNull(trimmed) || !trimmed.matches(Constants.CEP_REGEX)) {
            throw new IllegalArgumentException(Constants.CEP_INVALID_MESSAGE);
        }
        this.value = trimmed;
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

    @Override
    public String toString() {
        return value;
    }
}

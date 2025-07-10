package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public record Cep(String value) {

    public Cep {
        if (Objects.isNull(value) || !value.matches(Constants.CEP_REGEX)) {
            throw new IllegalArgumentException(Constants.ZIP_CODE_INVALID_MESSAGE);
        }
    }

    public List<String> generateFallbacks() {
        return Stream.iterate(
                value,
                prev -> !prev.equals(Constants.ZIP_CODE_NOT_FOUND_VALUE),
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
}
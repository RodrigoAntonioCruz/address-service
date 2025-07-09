package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.List;
import java.util.stream.Stream;

public record Cep(String value) {

    public Cep {
        if (value == null || !value.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido");
        }
    }

    public List<String> generateFallbacks() {
        return Stream.iterate(
                value,
                prev -> !prev.equals(Constants.INVALID_CEP),
                this::zeroRightmostDigit
        ).toList();
    }

    private String zeroRightmostDigit(String cep) {
        char[] digits = cep.toCharArray();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != '0') {
                digits[i] = '0';
                break;
            }
        }
        return new String(digits);
    }
}

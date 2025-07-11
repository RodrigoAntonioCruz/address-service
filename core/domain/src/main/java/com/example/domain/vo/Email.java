package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private final String value;
    public static final Pattern PATTERN = Pattern.compile(Constants.EMAIL_REGEX);

    public Email(String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException(Constants.EMAIL_INVALID);
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(Constants.EMAIL_INVALID);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

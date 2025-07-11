package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.Objects;
public class Password {
    private final String value;

    public Password(String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException(Constants.PASSWORD_NULL_OR_BLANK);
        }

        value = value.trim();

        if (isEncrypted(value)) {
            this.value = value;
            return;
        }

        if (value.length() < Constants.MINIMUM_LENGTH) {
            throw new IllegalArgumentException(Constants.PASSWORD_MIN_LENGTH);
        }

        if (!value.matches(Constants.PASSWORD_COMPLEXITY_REGEX)) {
            throw new IllegalArgumentException(Constants.PASSWORD_COMPLEXITY);
        }

        this.value = value;
    }

    private boolean isEncrypted(String value) {
        return value.startsWith("{bcrypt}") ||
                value.startsWith("$2a$") ||
                value.startsWith("$2b$") ||
                value.startsWith("$2y$");
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Constants.MASKED_PASSWORD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

package com.example.domain.vo;

import com.example.domain.utils.Constants;

import java.util.Objects;

public class Password {
    private final String value;

    public Password(final String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException(Constants.PASSWORD_NULL_OR_BLANK);
        }

        final String trimmedValue = value.trim();

        if (isEncrypted(trimmedValue)) {
            this.value = trimmedValue;
            return;
        }

        if (trimmedValue.length() < Constants.MINIMUM_LENGTH) {
            throw new IllegalArgumentException(Constants.PASSWORD_MIN_LENGTH);
        }

        if (!trimmedValue.matches(Constants.PASSWORD_COMPLEXITY_REGEX)) {
            throw new IllegalArgumentException(Constants.PASSWORD_COMPLEXITY);
        }

        this.value = trimmedValue;
    }

    private boolean isEncrypted(final String value) {
        return value.startsWith(Constants.BCRYPT_PREFIX)
                || value.startsWith(Constants.BCRYPT_PREFIX_2A)
                || value.startsWith(Constants.BCRYPT_PREFIX_2B)
                || value.startsWith(Constants.BCRYPT_PREFIX_2Y);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Constants.MASKED_PASSWORD;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Password that)) {
            return false;
        }
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

package com.example.domain.entities;
public record Address(
        String cep,
        String street,
        String neighborhood,
        String city,
        String state
) {
}

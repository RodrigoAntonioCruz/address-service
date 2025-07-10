package com.example.adapter.input.controller.dto.response;

public record AddressResponse(
        String street,
        String neighborhood,
        String city,
        String state
) { }

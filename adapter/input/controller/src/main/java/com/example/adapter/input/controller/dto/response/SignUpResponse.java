package com.example.adapter.input.controller.dto.response;

public record SignUpResponse(
        String id,
        String username,
        String name,
        String email,
        String cpf,
        String role
) {}

package com.example.adapter.input.controller.mapper;


import com.example.adapter.input.controller.dto.request.SignUpRequest;
import com.example.adapter.input.controller.dto.response.AuthResponse;
import com.example.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInputMapper {
    AuthResponse toResponse(User user);
    User toEntity(SignUpRequest userRequest);
}


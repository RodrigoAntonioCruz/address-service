package com.example.adapter.input.controller.mapper;

import com.example.adapter.input.controller.dto.request.SignUpRequest;
import com.example.adapter.input.controller.dto.response.AuthResponse;
import com.example.domain.entities.User;
import com.example.domain.enums.Role;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface UserInputMapper {
    @Mapping(target = "password", source = "password", qualifiedByName = "toPassword")
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "toCpf")
    @Mapping(target = "email", source = "email", qualifiedByName = "toEmail")
    @Mapping(target = "role", source = "role", qualifiedByName = "toRole")
    User toEntity(SignUpRequest request);
    AuthResponse toResponse(User user);

    @Named("toPassword")
    default Password toPassword(String value) {
        return new Password(value);
    }

    @Named("toCpf")
    default Cpf toCpf(String value) {
        return new Cpf(value);
    }

    @Named("toEmail")
    default Email toEmail(String value) {
        return new Email(value);
    }

    @Named("toRole")
    default Role toRole(String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return Role.valueOf(value.toUpperCase());
    }
}

package com.example.adapter.input.controller.mapper;

import com.example.adapter.input.controller.dto.request.SignUpRequest;
import com.example.adapter.input.controller.dto.response.SignUpResponse;
import com.example.domain.entities.User;
import com.example.domain.enums.Role;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserInputMapper {
    @Mapping(target = "password", source = "password", qualifiedByName = "toPassword")
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "toCpf")
    @Mapping(target = "email", source = "email", qualifiedByName = "toEmail")
    @Mapping(target = "role", source = "role", qualifiedByName = "toRole")
    User toEntity(SignUpRequest request);

    @Mapping(target = "email", source = "email", qualifiedByName = "fromEmail")
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "fromCpf")
    @Mapping(target = "role", source = "role", qualifiedByName = "fromRole")
    SignUpResponse toResponse(User user);

    @Named("toPassword")
    default Password toPassword(String value) {
        return value == null ? null : new Password(value);
    }

    @Named("toCpf")
    default Cpf toCpf(String value) {
        return value == null ? null : new Cpf(value);
    }

    @Named("toEmail")
    default Email toEmail(String value) {
        return value == null ? null : new Email(value);
    }

    @Named("toRole")
    default Role toRole(String value) {
        return value == null ? null : Role.valueOf(value.toUpperCase());
    }

    @Named("fromEmail")
    default String fromEmail(Email email) {
        return email == null ? null : email.getValue();
    }

    @Named("fromCpf")
    default String fromCpf(Cpf cpf) {
        return cpf == null ? null : cpf.getValue();
    }

    @Named("fromRole")
    default String fromRole(Role role) {
        return role == null ? null : role.name();
    }
}

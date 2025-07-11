package com.example.adapter.output.repository.user.mapper;

import com.example.adapter.output.repository.user.dto.CustomUserDetails;
import com.example.adapter.output.repository.user.entity.UserEntity;
import com.example.domain.entities.User;
import com.example.domain.enums.Role;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", imports = SimpleGrantedAuthority.class)
public interface UserOutputMapper {

    @Mapping(source = "password", target = "password", qualifiedByName = "passwordToString")
    @Mapping(source = "cpf", target = "cpf", qualifiedByName = "cpfToString")
    @Mapping(source = "email", target = "email", qualifiedByName = "emailToString")
    @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    UserEntity toEntity(User user, @Context PasswordEncoder encoder);

    @Mapping(source = "password", target = "password", qualifiedByName = "stringToPassword")
    @Mapping(source = "cpf", target = "cpf", qualifiedByName = "stringToCpf")
    @Mapping(source = "email", target = "email", qualifiedByName = "stringToEmail")
    @Mapping(source = "role", target = "role", qualifiedByName = "stringToRole")
    User toDomain(UserEntity userEntity);

    @Mapping(target = "password", expression = "java(user.getPassword().getValue())")
    @Mapping(target = "email", expression = "java(user.getEmail().getValue())")
    @Mapping(target = "authorities", expression = "java(mapAuthorities(user))")
    CustomUserDetails toCustomUserDetails(User user);

    default Collection<SimpleGrantedAuthority> mapAuthorities(User user) {
        if (user == null || user.getRole() == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }


    @Named("passwordToString")
    default String passwordToString(Password password, @Context PasswordEncoder encoder) {
        if (Objects.isNull(password)) return null;
        String raw = password.getValue();
        if (raw.startsWith("$2a$") || raw.startsWith("$2b$")) {
            return raw;
        }
        return encoder.encode(raw);
    }

    @Named("stringToPassword")
    default Password stringToPassword(String value) {
        return Objects.isNull(value) ? null : new Password(value);
    }

    @Named("cpfToString")
    default String cpfToString(Cpf cpf) {
        return Objects.isNull(cpf) ? null : cpf.getValue();
    }

    @Named("stringToCpf")
    default Cpf stringToCpf(String value) {
        return Objects.isNull(value) ? null : new Cpf(value);
    }

    @Named("emailToString")
    default String emailToString(Email email) {
        return Objects.isNull(email) ? null : email.getValue();
    }

    @Named("stringToEmail")
    default Email stringToEmail(String value) {
        return Objects.isNull(value) ? null : new Email(value);
    }

    @Named("roleToString")
    default String roleToString(Role role) {
        return Objects.isNull(role) ? null : role.name();
    }

    @Named("stringToRole")
    default Role stringToRole(String value) {
        return Objects.isNull(value) ? null : Role.valueOf(value.toUpperCase());
    }
}

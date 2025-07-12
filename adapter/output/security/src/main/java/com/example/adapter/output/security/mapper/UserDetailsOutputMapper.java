package com.example.adapter.output.security.mapper;

import com.example.adapter.output.security.dto.CustomUserDetails;
import com.example.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", imports = SimpleGrantedAuthority.class)
public interface UserDetailsOutputMapper {

    @Mapping(source = "password", target = "password", qualifiedByName = "toPasswordValue")
    @Mapping(source = "email", target = "email", qualifiedByName = "toEmailValue")
    @Mapping(target = "authorities", expression = "java(mapAuthorities(user))")
    CustomUserDetails toCustomUserDetails(User user);

    @org.mapstruct.Named("toPasswordValue")
    default String toPasswordValue(com.example.domain.vo.Password password) {
        return password != null ? password.getValue() : null;
    }

    @org.mapstruct.Named("toEmailValue")
    default String toEmailValue(com.example.domain.vo.Email email) {
        return email != null ? email.getValue() : null;
    }

    default Collection<SimpleGrantedAuthority> mapAuthorities(User user) {
        if (user == null || user.getRole() == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }
}

package com.example.domain.entities;

import com.example.domain.enums.Role;
import com.example.domain.utils.Constants;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -2466709349438262769L;

    private String id;
    private String username;
    private Password password;
    private String name;
    private Cpf cpf;
    private Email email;
    private Role role;

    public User(final String id,
                final String username,
                final Password password,
                final String name,
                final Cpf cpf,
                final Email email,
                final Role role) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.role = role;

        validate();
    }

    private void validate() {
        if (Objects.isNull(username) || username.isBlank()) {
            throw new IllegalArgumentException(Constants.USERNAME_REQUIRED);
        }
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException(Constants.NAME_NOT_NULL);
        }
        if (Objects.isNull(password)) {
            throw new IllegalArgumentException(Constants.PASSWORD_NOT_NULL);
        }
        if (Objects.isNull(cpf)) {
            throw new IllegalArgumentException(Constants.CPF_NOT_NULL);
        }
        if (Objects.isNull(email)) {
            throw new IllegalArgumentException(Constants.EMAIL_NOT_NULL);
        }
        if (Objects.isNull(role)) {
            throw new IllegalArgumentException(Constants.ROLE_NOT_NULL);
        }
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setId(final String id) {
        this.id = id;
    }

    // setters sem validação, pois validação está no construtor
    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final Password password) {
        this.password = password;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCpf(final Cpf cpf) {
        this.cpf = cpf;
    }

    public void setEmail(final Email email) {
        this.email = email;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", cpf=" + cpf +
                ", email=" + email +
                ", role=" + role +
                '}';
    }
}

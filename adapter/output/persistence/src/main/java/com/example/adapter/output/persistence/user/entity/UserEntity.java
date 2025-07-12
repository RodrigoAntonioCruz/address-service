package com.example.adapter.output.persistence.user.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@Document(collection = "users")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4193301264047593807L;

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private String password;
    private String name;
    private String cpf;
    private String role;
}

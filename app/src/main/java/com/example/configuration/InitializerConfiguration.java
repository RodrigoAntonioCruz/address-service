package com.example.configuration;

import com.example.adapter.output.persistence.user.UserPersistenceAdapter;
import com.example.domain.entities.User;
import com.example.domain.vo.Cpf;
import com.example.domain.vo.Email;
import com.example.domain.vo.Password;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.domain.enums.Role.ADMIN;

@Slf4j
@Configuration
public class InitializerConfiguration {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Bean
    public ApplicationRunner createAdminUser(UserPersistenceAdapter adapter) {
        return args -> {
            var exists = adapter.findByUsername(username);

            if (exists.isEmpty()) {
                User user = new User(
                        null,
                        username,
                        new Password(password),
                        "Operation System",
                        new Cpf("10440134005"),
                        new Email("app@sys.com"),
                        ADMIN
                );
                adapter.signUp(user);
                log.debug("Usuário ADMIN criado: " + username);
            } else {
                log.debug("Usuário já existe: " + username);
            }
        };
    }
}
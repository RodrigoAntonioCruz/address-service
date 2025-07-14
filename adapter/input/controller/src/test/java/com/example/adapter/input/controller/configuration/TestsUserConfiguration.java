package com.example.adapter.input.controller.configuration;

import com.example.adapter.input.controller.mapper.UserInputMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootConfiguration
@ComponentScan(basePackages = "com.example.adapter.input.controller.user")
@EnableAutoConfiguration(exclude = {
    SecurityAutoConfiguration.class,
    SecurityFilterAutoConfiguration.class,
    UserDetailsServiceAutoConfiguration.class
})
public class TestsUserConfiguration {
    @Bean
    @Primary
    public UserInputMapper userInputMapper() {
        return Mappers.getMapper(UserInputMapper.class);
    }
}



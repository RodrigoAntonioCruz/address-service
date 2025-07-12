package com.example.configuration;

import com.example.usecase.address.FindAddressByCepUseCase;
import com.example.usecase.address.ports.input.FindAddressByCepInputPort;
import com.example.usecase.address.ports.output.FindAddressByCepOutputPort;
import com.example.usecase.user.SignupUserUseCase;
import com.example.usecase.user.ports.input.SignUpInputPort;
import com.example.usecase.user.ports.output.SignUpOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class UseCaseConfiguration {
    @Bean
    public SignUpInputPort signupUserUseCase(SignUpOutputPort signUpOutputPort) {
        return new SignupUserUseCase(signUpOutputPort);
    }

    @Bean
    public FindAddressByCepInputPort findAddressByCepUseCase(FindAddressByCepOutputPort findAddressByCepOutputPort) {
        return new FindAddressByCepUseCase(findAddressByCepOutputPort);
    }
}
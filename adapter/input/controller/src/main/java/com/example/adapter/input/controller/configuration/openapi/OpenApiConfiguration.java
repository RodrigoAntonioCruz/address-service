package com.example.adapter.input.controller.configuration.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Value("${spring.app.version}")
    private String appVersion;


    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("API de Consulta de Endereços")
                .description("API responsável por realizar consultas de endereços brasileiros a partir do CEP informado.")
                .version(appVersion))
                .tags(List.of(
                      new Tag()
                          .name("Usuários")
                          .description("Endpoints responsáveis pelo cadastro e gerenciamento de informações de usuários."),
                      new Tag()
                          .name("Autenticação")
                          .description("Endpoints responsáveis por autenticação de usuários e geração de tokens de acesso."),
                      new Tag()
                          .name("Consulta de Endereços")
                          .description("Endpoints responsáveis por consultar endereços brasileiros a partir de um CEP informado.")
                ));

    }
}
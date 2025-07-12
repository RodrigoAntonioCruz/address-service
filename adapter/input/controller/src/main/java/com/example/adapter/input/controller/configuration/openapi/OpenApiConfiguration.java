package com.example.adapter.input.controller.configuration.openapi;

import com.example.adapter.input.controller.utils.Constants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
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
                .components(new Components().addSecuritySchemes(Constants.BASIC_AUTH_SECURITY_SCHEME,
                            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                .title("API de Consulta de Endereços")
                .description("API responsável por realizar consultas de endereços brasileiros a partir do CEP informado.")
                .version(appVersion))
                .tags(List.of(
                      new Tag()
                          .name("Cadastro de Usuário")
                          .description("Endpoint responsável pelo cadastro de usuários"),
                      new Tag()
                          .name("Consulta de Endereços")
                          .description("Endpoints responsáveis por consultar endereços brasileiros a partir de um CEP informado.")
                ));

    }
}
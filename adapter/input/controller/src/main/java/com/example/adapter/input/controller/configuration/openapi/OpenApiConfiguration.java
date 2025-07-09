package com.example.adapter.input.controller.configuration.openapi;

import com.example.adapter.input.controller.configuration.properties.PropertiesConfiguration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class OpenApiConfiguration {

    private final PropertiesConfiguration properties;

    @Bean
    public OpenAPI customOpenAPI() {

          return new OpenAPI()
                  .components(new Components())
                  .info(new Info()
                  .title("Product")
                  .description("API responsável por gerenciar as informações de produtos")
                  .version(properties.getAppVersion()))
                  .tags(
                          List.of(new Tag().name("Product").description("Endpoints responsáveis por gerenciar as informações de produtos")
               )
          );
    }
}
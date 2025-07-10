package com.example.adapter.input.controller.configuration.cors;

import com.example.adapter.input.controller.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
public class CorsFilterConfiguration {

    private final Environment environment;

    private static final String[] ALLOWED_HEADERS = {
            HttpHeaders.ACCEPT,
            HttpHeaders.AUTHORIZATION,
            HttpHeaders.CONTENT_TYPE
    };

    private static final String[] ALLOWED_METHODS = {
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.OPTIONS.name(),
            HttpMethod.HEAD.name()
    };

    @Bean
    public CorsFilter cors() {
        CorsConfiguration configuration = new CorsConfiguration();

        String originsProperty = environment.getProperty(
                Constants.CORS_ALLOWED_ORIGINS_PROPERTY, Constants.CORS_DEFAULT_ORIGINS
        );

        if (originsProperty.isBlank()) {
            throw new IllegalStateException(
                    String.format("CORS está mal configurado! Defina a propriedade %s no application.properties", Constants.CORS_ALLOWED_ORIGINS_PROPERTY));
        }

        List<String> allowedOrigins = List.of(originsProperty.split(","));

        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(List.of(ALLOWED_METHODS));
        configuration.setAllowedHeaders(List.of(ALLOWED_HEADERS));
        configuration.setExposedHeaders(List.of(ALLOWED_HEADERS));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(Constants.CORS_MAX_AGE_SECONDS);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(Constants.CORS_URL_PATTERN, configuration);
        return new CorsFilter(source);
    }
}
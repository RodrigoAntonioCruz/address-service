package com.example.adapter.input.controller.dto.request;

import com.example.adapter.input.controller.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @Schema(example = Constants.USERNAME_EXAMPLE, description = Constants.USERNAME_DESCRIPTION)
        @NotBlank(message = Constants.USERNAME_REQUIRED)
        String username,

        @Schema(example = Constants.PASSWORD_EXAMPLE, description = Constants.PASSWORD_DESCRIPTION)
        @NotBlank(message = Constants.PASSWORD_REQUIRED)
        String password) {
}

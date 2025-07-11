package com.example.adapter.input.controller.dto.request;

import com.example.adapter.input.controller.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @Schema(example = Constants.USERNAME_EXAMPLE, description = Constants.USERNAME_DESCRIPTION)
        @NotBlank(message = Constants.USERNAME_REQUIRED)
        @Size(min = Constants.USERNAME_MIN, max = Constants.USERNAME_MAX, message = Constants.USERNAME_LENGTH)
        String username,

        @Schema(example = Constants.PASSWORD_EXAMPLE, description = Constants.PASSWORD_DESCRIPTION)
        @NotBlank(message = Constants.PASSWORD_REQUIRED)
        @Size(min = Constants.PASSWORD_MIN, max = Constants.PASSWORD_MAX, message = Constants.PASSWORD_LENGTH)
        String password,

        @Schema(example = Constants.NAME_EXAMPLE, description = Constants.NAME_DESCRIPTION)
        @NotBlank(message = Constants.NAME_REQUIRED)
        @Size(min = Constants.NAME_MIN, max = Constants.NAME_MAX, message = Constants.NAME_LENGTH)
        String name,

        @Schema(example = Constants.CPF_EXAMPLE, description = Constants.CPF_DESCRIPTION)
        @NotBlank(message = Constants.CPF_REQUIRED)
        @Pattern(regexp = Constants.CPF_REGEX, message = Constants.CPF_INVALID)
        String cpf,

        @Schema(example = Constants.EMAIL_EXAMPLE, description = Constants.EMAIL_DESCRIPTION)
        @NotBlank(message = Constants.EMAIL_REQUIRED)
        @Email(message = Constants.EMAIL_INVALID)
        String email,

        @Schema(example = Constants.ROLE_EXAMPLE, description = Constants.ROLE_DESCRIPTION)
        @NotBlank(message = Constants.ROLE_REQUIRED)
        @Pattern(regexp = Constants.ROLE_REGEX, flags = Pattern.Flag.CASE_INSENSITIVE, message = Constants.ROLE_INVALID)
        String role

) {}

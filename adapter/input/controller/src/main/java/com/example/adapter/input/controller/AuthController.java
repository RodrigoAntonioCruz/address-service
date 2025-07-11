package com.example.adapter.input.controller;

import com.example.adapter.input.controller.dto.request.SignInRequest;
import com.example.adapter.input.controller.dto.request.SignUpRequest;
import com.example.adapter.input.controller.dto.response.AuthResponse;
import com.example.adapter.input.controller.mapper.UserInputMapper;
import com.example.adapter.input.controller.utils.Constants;
import com.example.usecase.user.ports.input.SignInInputPort;
import com.example.usecase.user.ports.input.SignUpInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Autenticação")
@RequestMapping("/auth")
public class AuthController {

    private final UserInputMapper mapper;

    private final SignUpInputPort signUpInputPort;

    private final SignInInputPort signInInputPort;

    @PostMapping("/signup")
    @Operation(summary = Constants.DESCRIPTION_SIGNUP)
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.STATUS_CODE_201, description = Constants.RESPONSE_201_CREATE),
            @ApiResponse(responseCode = Constants.STATUS_CODE_400, description = Constants.RESPONSE_400_VALIDATION_ERROR),
            @ApiResponse(responseCode = Constants.STATUS_CODE_403, description = Constants.RESPONSE_403_PERMISSION_DENIED),
            @ApiResponse(responseCode = Constants.STATUS_CODE_404, description = Constants.RESPONSE_404_NOT_FOUND),
            @ApiResponse(responseCode = Constants.STATUS_CODE_409, description = Constants.RESPONSE_409_CONFLICT),
            @ApiResponse(responseCode = Constants.STATUS_CODE_500, description = Constants.RESPONSE_500_INTERNAL_ERROR),
            @ApiResponse(responseCode = Constants.STATUS_CODE_503, description = Constants.RESPONSE_503_SERVICE_UNAVAILABLE_ERROR)
    })
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Início da criação de um usuário ", Constants.LOG_METHOD_SIGNUP, signUpRequest);

        var user = mapper.toEntity(signUpRequest);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim da criação de um usuário ", Constants.LOG_METHOD_SIGNUP, signUpRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.toResponse(
                      signUpInputPort.signUp(user)
                )
        );
    }

    @PostMapping("/signin")
    @Operation(summary = Constants.DESCRIPTION_SIGNIN)
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.STATUS_CODE_200, description = Constants.RESPONSE_200_OK),
            @ApiResponse(responseCode = Constants.STATUS_CODE_400, description = Constants.RESPONSE_400_VALIDATION_ERROR),
            @ApiResponse(responseCode = Constants.STATUS_CODE_401, description = Constants.RESPONSE_401_ACCESS_DENIED),
            @ApiResponse(responseCode = Constants.STATUS_CODE_403, description = Constants.RESPONSE_403_PERMISSION_DENIED),
            @ApiResponse(responseCode = Constants.STATUS_CODE_404, description = Constants.RESPONSE_404_NOT_FOUND),
            @ApiResponse(responseCode = Constants.STATUS_CODE_500, description = Constants.RESPONSE_500_INTERNAL_ERROR),
            @ApiResponse(responseCode = Constants.STATUS_CODE_503, description = Constants.RESPONSE_503_SERVICE_UNAVAILABLE_ERROR)
    })
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Início da autenticação de um usuário", Constants.LOG_METHOD_SIGNIN, signInRequest);

        var response = mapper.toResponse(
                signInInputPort.signIn(
                        signInRequest.username(),
                        signInRequest.password()
                )
        );

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim  da autenticação de um usuário", Constants.LOG_METHOD_SIGNIN, signInRequest);

        return ResponseEntity.ok(response);
    }
}

package com.example.adapter.input.controller;

import com.example.adapter.input.controller.dto.response.AddressResponse;
import com.example.adapter.input.controller.mapper.AddressInputMapper;
import com.example.adapter.input.controller.utils.Constants;
import com.example.usecase.address.ports.input.FindAddressByCepInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Consulta de Endereços")
@RequestMapping("/addresses")
public class AddressController {

    private final AddressInputMapper mapper;

    private final FindAddressByCepInputPort findAddressByCepInputPort;

    @GetMapping("/{cep}")
    @Operation(summary = Constants.DESCRIPTION_FIND_ADDRESS_BY_CEP)
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.STATUS_CODE_200, description = Constants.RESPONSE_200_GET),
            @ApiResponse(responseCode = Constants.STATUS_CODE_400, description = Constants.RESPONSE_400_VALIDATION_ERROR),
            @ApiResponse(responseCode = Constants.STATUS_CODE_401, description = Constants.RESPONSE_401_ACCESS_DENIED),
            @ApiResponse(responseCode = Constants.STATUS_CODE_404, description = Constants.RESPONSE_404_NOT_FOUND),
            @ApiResponse(responseCode = Constants.STATUS_CODE_500, description = Constants.RESPONSE_500_INTERNAL_ERROR),
            @ApiResponse(responseCode = Constants.STATUS_CODE_503, description = Constants.RESPONSE_503_SERVICE_UNAVAILABLE_ERROR)
    })
    public ResponseEntity<AddressResponse> findAddressByCep(@PathVariable String cep) {
        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_CEP,
                "Início da busca de um endereço por CEP", Constants.LOG_METHOD_FIND_ADDRESS, cep);

        var response = mapper.toResponse(
                findAddressByCepInputPort.findAddressByCep(cep)
        );

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_CEP,
                "Fim da busca de um endereço por CEP", Constants.LOG_METHOD_FIND_ADDRESS, cep);

        return ResponseEntity.ok(response);
    }
}

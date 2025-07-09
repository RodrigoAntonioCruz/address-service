package com.example.adapter.output.dto.response;

package com.example.adapter.output.client.dto;

import lombok.Builder;

@Builder
public record ViaCepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {
}

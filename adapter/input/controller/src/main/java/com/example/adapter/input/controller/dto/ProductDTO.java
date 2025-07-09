package com.example.adapter.input.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5237884468516326061L;

    @Schema(defaultValue = "Identificador do produto", example = "64889fb6f3a692337867ea95")
    private String id;

    @Schema(defaultValue = "Identificador da empresa", example = "52189fb6f3a635647867ec64")
    private String tenantId;

    @Schema(defaultValue = "Descrição do produto", example = "Caneta esferográfica Bic")
    private String description;

    @Schema(defaultValue = "Preço do produto", example = "15.20")
    private BigDecimal value;
}

package com.example.adapter.input.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@AllArgsConstructor
@JsonIgnoreProperties({"id", "tenantId"})
@EqualsAndHashCode(callSuper = true)
public class ProductRequest extends ProductDTO {
}

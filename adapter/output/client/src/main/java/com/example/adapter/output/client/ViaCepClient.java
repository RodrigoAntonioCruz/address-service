package com.example.adapter.output.client;

import com.example.adapter.output.dto.response.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "${feign.client.viacep-url}")
public interface ViaCepClient {
    @GetMapping("/{cep}/json/")
    ViaCepResponse getAddress(@PathVariable("cep") String cep);

}
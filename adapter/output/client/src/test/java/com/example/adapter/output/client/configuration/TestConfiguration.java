package com.example.adapter.output.client.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
        "com.example.adapter.output.client"
})
@EnableFeignClients(basePackages = {
        "com.example.adapter.output.client"
})
public class TestConfiguration {

}


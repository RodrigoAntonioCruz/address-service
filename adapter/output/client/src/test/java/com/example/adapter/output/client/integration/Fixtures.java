package com.example.adapter.output.client.integration;

public class Fixtures {
    public static final String CB_NAME = "viaCepClient";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String VALID_CEP = "01001000";
    public static final String VALID_CEP_FORMATTED = "01001-000";
    public static final String JSON_INVALID = "INVALID JSON";
    public static final String URL_PATH = "/" + VALID_CEP + "/json/";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String SERVICE_UNAVAILABLE_MESSAGE = "O serviço VIA CEP está temporariamente indisponível";
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int EXPECTED_RETRY_ATTEMPTS = 3;
    public static final int CIRCUIT_BREAKER_TRIGGER_ATTEMPTS = 5;
    public static final String RESPONSE_STREET = "Praça da Sé";
    public static final String RESPONSE_NEIGHBORHOOD = "Sé";
    public static final String JSON_PARTIAL_RESPONSE = """
            {
                "cep": "01001-000"
            }
            """;
    public static final String JSON_SUCCESS_RESPONSE = """
            {
                "cep": "01001-000",
                "logradouro": "Praça da Sé",
                "bairro": "Sé",
                "localidade": "São Paulo",
                "uf": "SP"
            }
            """;
}

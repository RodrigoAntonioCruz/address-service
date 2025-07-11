package com.example.adapter.input.controller.utils;

public class Constants {

    private Constants() {
    }

    /**
     * LOG_KEY
     */
    public static final String LOG_KEY_ID = "id={} ";
    public static final String LOG_KEY_CLASS = "class={} ";
    public static final String LOG_KEY_METHOD = "method={} ";
    public static final String LOG_KEY_MESSAGE = "msg=\"{}\" ";
    public static final String LOG_KEY_ENTITY = "entity=\"{}\" ";
    public static final String LOG_KEY_EVENT = "event={} ";
    public static final String LOG_KEY_DESCRIPTION = "description=\"{}\" ";
    public static final String LOG_KEY_HTTP_CODE = "httpCode={} ";
    public static final String LOG_KEY_CEP = "cep={} ";

    /**
     * LOG_METHOD
     * */
    public static final String LOG_METHOD_FIND_ADDRESS = "findAddressByCep";
    public static final String LOG_METHOD_BUSINESS_EXCEPTION = "BusinessException";
    public static final String LOG_METHOD_BIND_EXCEPTION = "BindException";
    public static final String LOG_METHOD_ILLEGAL_ARGUMENT = "IllegalArgumentException";
    public static final String LOG_METHOD_CONSTRAINT_VIOLATION_EXCEPTION = "ConstraintViolationException";
    public static final String LOG_METHOD_CLIENT_ABORT_EXCEPTION = "ClientAbortException";
    public static final String LOG_METHOD_HTTP_MESSAGE_NOT_READABLE_EXCEPTION = "HttpMessageNotReadableException";
    public static final String LOG_METHOD_HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION = "HttpRequestMethodNotSupportedException";
    public static final String LOG_METHOD_IO_EXCEPTION = "IOException";
    public static final String LOG_METHOD_MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION = "MissingServletRequestParameterException";
    public static final String LOG_METHOD_METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION = "MethodArgumentTypeMismatchException";
    public static final String LOG_METHOD_METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "MethodArgumentNotValidException";
    public static final String LOG_METHOD_DUPLICATE = "DuplicateRequestException";
    public static final String LOG_METHOD_NUMBER_FORMAT_EXCEPTION = "NumberFormatException";
    public static final String LOG_METHOD_NOT_FOUND_EXCEPTION = "NotFoundException";
    public static final String LOG_METHOD_NOT_AVAILABLE_EXCEPTION = "not available";
    public static final String LOG_METHOD_CURRENT_TIMESTAMP = "dd/MM/yyyy HH:mm:ss";
    public static final String SHOULD_BE = " should be ";
    public static final String LOG_METHOD_THROWABLE = "Throwable";
    public static final String LOG_METHOD_SIGNUP = "signUp";
    public static final String LOG_METHOD_SIGNIN = "signIn";

    /**
     * LOG_OTHER
     */
    public static final String LOG_EXCEPTION = "exception={} ";
    public static final String TRACE_ID_KEY = "traceId";


    /**
     * MESSAGES_API_DOCS
     */
    public static final String RESPONSE_201_CREATE = "Criação realizada com sucesso, dados retornados no corpo da resposta";
    public static final String RESPONSE_200_GET = "Busca realizada com sucesso, dados retornados no corpo da resposta";
    public static final String RESPONSE_200_OK = "Requisição realizada com sucesso, dados retornados no corpo da resposta.";
    public static final String RESPONSE_400_VALIDATION_ERROR = "Erro de validação: os dados informados possuem inconsistências";
    public static final String RESPONSE_401_ACCESS_DENIED = "Acesso negado: autenticação necessária";
    public static final String RESPONSE_403_PERMISSION_DENIED = "Permissão negada: você não tem autorização para acessar este recurso";
    public static final String RESPONSE_404_NOT_FOUND = "Não encontrado: registro solicitado não existe";
    public static final String RESPONSE_409_CONFLICT = "Conflito: os dados informados já existem no sistema";
    public static final String RESPONSE_500_INTERNAL_ERROR = "Erro interno: o sistema está indisponível no momento";
    public static final String RESPONSE_503_SERVICE_UNAVAILABLE_ERROR = "O serviço está temporariamente indisponível";
    public static final String DESCRIPTION_SIGNIN = "Autentica o usuário e retorna o token de acesso";
    public static final String DESCRIPTION_SIGNUP = "Cria um novo usuário no sistema";
    public static final String DESCRIPTION_FIND_ADDRESS_BY_CEP = "Busca o endereço correspondente ao CEP informado";
    public static final String STATUS_CODE_200 = "200";
    public static final String STATUS_CODE_201 = "201";
    public static final String STATUS_CODE_400 = "400";
    public static final String STATUS_CODE_401 = "401";
    public static final String STATUS_CODE_403 = "403";
    public static final String STATUS_CODE_404 = "404";
    public static final String STATUS_CODE_409 = "409";
    public static final String STATUS_CODE_500 = "500";
    public static final String STATUS_CODE_503 = "503";
    public static final String CORS_ALLOWED_ORIGINS_PROPERTY = "cors.allowed-origins";
    public static final String CORS_DEFAULT_ORIGINS = "";
    public static final String CORS_URL_PATTERN = "/**";
    public static final long CORS_MAX_AGE_SECONDS = 3600L;
    public static final String BASIC_AUTH_SECURITY_SCHEME = "basicAuth";

}

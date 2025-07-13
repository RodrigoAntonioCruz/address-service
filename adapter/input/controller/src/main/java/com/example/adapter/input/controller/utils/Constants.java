package com.example.adapter.input.controller.utils;

public class Constants {

    private Constants() {
    }

    /**
     * LOG_KEY
     */
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
    public static final String RESPONSE_400_VALIDATION_ERROR = "Erro de validação: os dados informados possuem inconsistências";
    public static final String RESPONSE_401_ACCESS_DENIED = "Acesso negado: autenticação necessária";
    public static final String RESPONSE_403_PERMISSION_DENIED = "Permissão negada: você não tem autorização para acessar este recurso";
    public static final String RESPONSE_404_NOT_FOUND = "Não encontrado: registro solicitado não existe";
    public static final String RESPONSE_409_CONFLICT = "Conflito: os dados informados já existem no sistema";
    public static final String RESPONSE_500_INTERNAL_ERROR = "Erro interno: o sistema está indisponível no momento";
    public static final String RESPONSE_503_SERVICE_UNAVAILABLE_ERROR = "O serviço está temporariamente indisponível";
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

    /**
     * OTHERS
     */
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final int USERNAME_MIN = 3;
    public static final int USERNAME_MAX = 50;
    public static final String USERNAME_REQUIRED = "O username é obrigatório";
    public static final String USERNAME_LENGTH = "O username deve ter entre 3 e 50 caracteres";
    public static final String USERNAME_EXAMPLE = "joaosilva";
    public static final String USERNAME_DESCRIPTION = "Nome de usuário único no sistema";
    public static final int PASSWORD_MIN = 8;
    public static final int PASSWORD_MAX = 100;
    public static final String PASSWORD_REQUIRED = "A senha é obrigatória";
    public static final String PASSWORD_LENGTH = "A senha deve ter no mínimo 8 e no máximo 100 caracteres";
    public static final String PASSWORD_EXAMPLE = "P@ssw0rd!";
    public static final String PASSWORD_DESCRIPTION = "Senha do usuário, deve conter letra, número e caractere especial";
    public static final int NAME_MIN = 3;
    public static final int NAME_MAX = 100;
    public static final String NAME_REQUIRED = "O nome é obrigatório";
    public static final String NAME_LENGTH = "O nome deve ter entre 3 e 100 caracteres";
    public static final String NAME_EXAMPLE = "João Lima e Silva";
    public static final String NAME_DESCRIPTION = "Nome completo do usuário";
    public static final String CPF_REQUIRED = "O CPF é obrigatório";
    public static final String CPF_REGEX = "\\d{11}";
    public static final String CPF_INVALID = "O CPF deve conter 11 dígitos numéricos";
    public static final String CPF_EXAMPLE = "73786275009";
    public static final String CPF_DESCRIPTION = "CPF do usuário (somente números)";
    public static final String EMAIL_REQUIRED = "O e-mail é obrigatório";
    public static final String EMAIL_INVALID = "O e-mail informado é inválido";
    public static final String EMAIL_EXAMPLE = "joaosilva@gmail.com";
    public static final String EMAIL_DESCRIPTION = "E-mail válido do usuário";
    public static final String ROLE_REQUIRED = "O papel (role) é obrigatório";
    public static final String ROLE_REGEX = "ADMIN|USER";
    public static final String ROLE_INVALID = "O papel deve ser ADMIN ou USER";
    public static final String ROLE_EXAMPLE = "ADMIN";
    public static final String ROLE_DESCRIPTION = "Papel do usuário no sistema. Valores aceitos: ADMIN ou USER";
}

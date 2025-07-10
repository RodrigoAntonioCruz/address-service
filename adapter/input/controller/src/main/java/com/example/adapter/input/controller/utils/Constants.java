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
    public static final String LOG_METHOD_EMPTY_RESULT_DATA_ACCESS_EXCEPTION = "EmptyResultDataAccessException";
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
    public static final String LOG_METHOD_SECURITY_EXCEPTION = "SecurityException";
    public static final String LOG_METHOD_THROWABLE = "Throwable";

    public static final String LOG_METHOD_RECEIVE = "receive ";
    public static final String LOG_METHOD_PUBLISH = "publish ";
    public static final String LOG_METHOD_GET_MESSAGE = "getMessage";
    public static final String LOG_METHOD_RECOVERY_DLQ = "recovery-dlq";
    public static final Object LOG_METHOD_RECOVERY_RETRY = "recovery-retry";
    public static final String LOG_METHOD_SEND_MESSAGE = "sendMessage";

    /**
     * LOG_OTHER
     */
    public static final String ID = "id";
    public static final String TENANT_HEADER = "X-Tenant-ID";
    public static final String LOG_EXCEPTION = "exception={} ";
    public static final String X_CP_TRACEID = "X-cp-traceid";
    public static final String TRACE_ID_KEY = "traceId";
    public static final String EMPTY_SPACE = " ";
    public static final String EMPTY_SPACE_COLON = ": ";
    public static final String COLON = ":";
    public static final String CLIENT_CLOSED_REQUEST = "Client Closed Request";
    public static final String SEND_TO_REPROCESS = " enviando para fila de reprocessamento ";
    public static final String SESSION_ID_KEY = "sessionId";
    public static final String RETRY_KEY = "x-retry";
    public static final String REGEX = "^(.*?)(queue|$)";
    public static final String DASH = " - ";
    public static final String N_A = "N/A";
    public static final Integer ZERO = 0;

    /**
     * ATTRIBUTES_NAME
     */
    public static final String UNIQUE = "unique";
    public static final String TENANT_ID = "tenantId";
    public static final String PATIENT_ID = "patientId";
    public static final String STATUS = "status";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    public static final String START = "start";
    public static final String END = "end";
    public static final String ZIP_CODE = "zipCode";
    public static final String PROCEDURE = "procedure";
    public static final String NAME = "name";
    public static final String CPF = "cpf";
    public static final String EMAIL = "email";
    public static final String REGISTER = "register";
    public static final String  USERS = "users";
    public static final String  DEBITS = "debits";
    public static final String  AGENDAS = "agendas";
    public static final String  BUDGETS = "budgets";
    public static final String  PATIENTS = "patients";
    public static final String  ADDRESSES = "addresses";
    public static final String  ANAMNESIS = "anamnesis";
    public static final String  DOCUMENTS = "documents";
    public static final String  HEALTH_PLANS = "healthPlans";
    public static final String ANS = "ans";
    public static final String HEALTH_PLAN_IDS = "healthPlanIds";
    public static final String DOCTOR_IDS = "doctorIds";

    /**
     * MESSAGES_LOG_AND_TRHOWS
     */
    public static final String NON_MANDATORY_IMPLEMENTATION_METHOD = "Este método não é implementado obrigatoriamente";

    /**
     * MESSAGES_API_DOCS
     */
    public static final String RESPONSE_201_CREATE = "Criação realizada com sucesso, dados retornados no corpo da resposta";
    public static final String RESPONSE_200_UPDATE = "Atualização realizada com sucesso, dados retornados no corpo da resposta";
    public static final String RESPONSE_200_PARTIAL_UPDATE = "Atualização parcial realizada com sucesso, dados retornados no corpo da resposta";
    public static final String RESPONSE_200_GET = "Busca realizada com sucesso, dados retornados no corpo da resposta";
    public static final String RESPONSE_204_UPLOAD = "Upload realizada com sucesso, sem conteúdo no corpo da resposta";
    public static final String RESPONSE_204_REQUEST = "Requisição realizada com sucesso, sem conteúdo no corpo da resposta";
    public static final String RESPONSE_204_DELETE = "Registro removido com sucesso, sem conteúdo no corpo da resposta";
    public static final String RESPONSE_204_DELETE_MULTIPLE = "Registros removidos com sucesso, sem conteúdo no corpo da resposta";
    public static final String RESPONSE_400_VALIDATION_ERROR = "Erro de validação: os dados informados possuem inconsistências";
    public static final String RESPONSE_401_ACCESS_DENIED = "Acesso negado: autenticação necessária";
    public static final String RESPONSE_403_PERMISSION_DENIED = "Permissão negada: você não tem autorização para acessar este recurso";
    public static final String RESPONSE_404_NOT_FOUND = "Não encontrado: registro solicitado não existe";
    public static final String RESPONSE_404_NOT_FOUND_MULTIPLE = "Não encontrado: um ou mais registros solicitados não existem";
    public static final String RESPONSE_409_CONFLICT = "Conflito: os dados informados já existem no sistema";
    public static final String RESPONSE_500_INTERNAL_ERROR = "Erro interno: o sistema está indisponível no momento";
    public static final String RESPONSE_503_SERVICE_UNAVAILABLE_ERROR = "O serviço está temporariamente indisponível";
    public static final String DESCRIPTION_CREATE = "Cria um novo registro";
    public static final String DESCRIPTION_UPLOAD = "Upload de um novo arquivo";
    public static final String DESCRIPTION_UPDATE = "Atualiza um registro existente";
    public static final String DESCRIPTION_PARTIAL_UPDATE = "Atualiza parcialmente um registro existente";
    public static final String DESCRIPTION_FIND_BY_ID = "Busca um registro por id";
    public static final String DESCRIPTION_FIND_ADDRESS_BY_CEP = "Busca um endereço por CEP";
    public static final String DESCRIPTION_FIND_BY_FILTER = "Busca paginada de registros por filtros";
    public static final String DESCRIPTION_DELETE = "Remove um registro existente";
    public static final String DESCRIPTION_DELETE_ALL_BY_ID = "Remove vários registros existentes";
    public static final String DESCRIPTION_CREATE_BATCH = "Criar múltiplos registros.";
    public static final String DESCRIPTION_FIND_ALL = "Buscar todos os registros sem paginação.";
    public static final String DESCRIPTION_COUNT = "Obter quantidade total de registros.";
    public static final String DESCRIPTION_EXISTS = "Verificar se existe um registro pelo filtro informado.";
    public static final String DESCRIPTION_ACTIVATE = "Ativar um registro.";
    public static final String DESCRIPTION_DEACTIVATE = "Desativar um registro.";
    public static final String DESCRIPTION_CHANGE_STATUS = "Alterar o status do registro.";
    public static final String DESCRIPTION_DOWNLOAD = "Download do arquivo do registro.";
    public static final String DESCRIPTION_HISTORY = "Buscar histórico de alterações do registro.";
    public static final String DESCRIPTION_EXPORT = "Exportar dados do módulo.";
    public static final String DESCRIPTION_IMPORT = "Importar dados em massa.";
    public static final String DESCRIPTION_VALIDATE = "Validar dados do registro.";
    public static final String STATUS_CODE_200 = "200";
    public static final String STATUS_CODE_201 = "201";
    public static final String STATUS_CODE_204 = "204";
    public static final String STATUS_CODE_400 = "400";
    public static final String STATUS_CODE_401 = "401";
    public static final String STATUS_CODE_403 = "403";
    public static final String STATUS_CODE_404 = "404";
    public static final String STATUS_CODE_409 = "409";
    public static final String STATUS_CODE_500 = "500";
    public static final String STATUS_CODE_503 = "503";
    public static final String FIELD_START = "start";
    public static final String FIELD_END = "end";
    public static final String FIELD_CREATED_AT = "createdAt";
    public static final String FIELD_UPDATED_AT = "updatedAt";
    public static final String FIELD_DELETED_AT = "deletedAt";
    public static final String PREFIX_SE = "se=";
    public static final String PREFIX_S = "s=";
    public static final String PREFIX_E = "e=";
    public static final String PREFIX_C = "c=";
    public static final String PREFIX_U = "u=";
    public static final String PREFIX_D = "d=";
    public static final String SUFFIX_START_OF_DAY = "T00:00:00Z";
    public static final String SUFFIX_END_OF_DAY = "T23:59:59Z";
    public static final String ORIGINAL_ROUTING_KEY = "x-original-routing-key";

}

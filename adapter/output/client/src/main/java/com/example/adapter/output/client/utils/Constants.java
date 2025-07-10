package com.example.adapter.output.client.utils;


public class Constants {

    private Constants() {
    }

    /**
     * LOG_KEY
     */
    public static final String LOG_KEY_METHOD = "method={} ";
    public static final String LOG_KEY_MESSAGE = "msg=\"{}\" ";
    public static final String LOG_KEY_CEP = "cep={} ";
    public static final String LOG_KEY_THROWABLE = "throwable={} ";

    /**
     * LOG_METHOD
     * */
    public static final String LOG_METHOD_FIND_ADDRESS = "findAddressByCep";
    public static final String LOG_METHOD_ALIAS_ADDRESS = "saveAddressAlias";
    public static final String LOG_METHOD_FALLBACK_FIND_ADDRESS = "fallbackFindAddress";
    public static final String USER_NOT_FOUND = "Usuário não encontrado";

}

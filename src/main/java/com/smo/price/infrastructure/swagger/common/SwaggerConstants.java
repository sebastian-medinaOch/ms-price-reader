package com.smo.price.infrastructure.swagger.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SwaggerConstants {

    // Configuración OpenAPI principal
    public static final String UTILITY_OPEN_API_GROUP = "price";
    public static final String UTILITY_OPEN_API_PATHS_TO_MATCH = "/price/**";
    public static final String UTILITY_OPEN_API_PATHS_TO_MATCH_ORIGINAL = "/price/v1";
    public static final String UTILITY_OPEN_API_INFO_TITLE = "Price Query API - Price Microservice";
    public static final String UTILITY_OPEN_API_INFO_DESCRIPTION = "This microservice allows you to check the applicable price of a product based on established parameters.";
    public static final String UTILITY_OPEN_API_INFO_VERSION = "1.0.0";
    public static final String UTILITY_OPEN_API_INFO_CONTACT_NAME = "Sebastian Medina Ochoa - Backend Developer";
    public static final String UTILITY_OPEN_API_INFO_CONTACT_URL = "https://www.linkedin.com/in/sebastian-medina-ochoa-3b69001aa/";
    public static final String UTILITY_OPEN_API_INFO_CONTACT_EMAIL = "sebasthyy1@gmail.com";
    public static final String UTILITY_OPEN_API_INFO_TERMS_OF_SERVICE = "https://www.linkedin.com/in/sebastian-medina-ochoa-3b69001aa/";
    public static final String UTILITY_OPEN_API_INFO_LICENCE_NAME = "S.M.O";
    public static final String UTILITY_OPEN_API_INFO_LICENCE_URL = "https://www.linkedin.com/in/sebastian-medina-ochoa-3b69001aa/";
    public static final String UTILITY_OPEN_API_SCAN_PACKAGE = "com.smo.price";
    public static final String UTILITY_OPEN_API_SERVER_URL = "http://localhost:8080/price/v1";
    public static final String UTILITY_OPEN_API_SERVER_DESCRIPTION = "URL for local testing";

}

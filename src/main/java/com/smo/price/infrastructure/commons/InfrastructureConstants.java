package com.smo.price.infrastructure.commons;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InfrastructureConstants {

    //Path
    public static final String PATH_PRODUCT_CONTROLLER = "/price/v1";
    public static final String PATH_GET_PRICE_CONTROLLER = "/get";

    //Paths ands headers
    public static final String PARAM_APPLICATION_DATE = "applicationDate";
    public static final String HEADER_FLOW_ID = "flowId";
    public static final String PARAM_PRODUCT_ID = "productId";
    public static final String PARAM_BRAND_ID = "brandId";

    //MessageError
    public static final String MESSAGE_GET_PRICE_NOT_FOUND = "No prices were found for the given parameters: application date, product ID, and brand ID.";

    //Message
    public static final String RETRIEVE_SUCCESS_MESSAGE = "Query Generated Successfully..";

    //Log
    public static final String LOG_EXCEPTION_MESSAGE = "An error occurred: FlowId: {}, ErrorMessage: {}";

}

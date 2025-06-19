package com.smo.price.infrastructure.commons;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InfrastructureConstants {

    //Path
    public static final String PATH_PRODUCT_CONTROLLER = "/price/v1";
    public static final String PATH_GET_PRICE_CONTROLLER = "/get";

    //Params ands headers
    public static final String PARAM_APPLICATION_DATE = "applicationDate";
    public static final String HEADER_FLOW_ID = "flowId";
    public static final String PARAM_PRODUCT_ID = "productId";
    public static final String PARAM_BRAND_ID = "brandId";

    //MessageError
    public static final String MESSAGE_FLOW_ID_NOT_BLANK = "The 'flowId' header is required and cannot be empty.";
    public static final String MESSAGE_BRAND_ID_NOT_BLANK = "The 'brandId' header is required and cannot be empty.";
    public static final String MESSAGE_PRODUCT_ID_NOT_BLANK = "The 'productId' header is required and cannot be empty.";
    public static final String MESSAGE_APPLICATION_DATE_NOT_BLANK = "The 'applicationDate' header is required and cannot be empty.";

    //Message
    public static final String RETRIEVE_SUCCESS_MESSAGE = "Query Generated Successfully..";

    //Log
    public static final String LOG_EXCEPTION_MESSAGE = "An error occurred: FlowId: {}, ErrorMessage: {}";
    public static final String LOG_STRING_INIT_FLOW = "FlowId {} - Start of getPrice flow | applicationDate={}, productId={}, brandId={}";
    public static final String LOG_STRING_END_FLOW = "FlowId {} - Successful completion of getPrice flow | response={}";

}

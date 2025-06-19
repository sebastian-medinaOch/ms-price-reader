package com.smo.price.infrastructure.commons;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionsConstants {

    /* Constantes Extras de utilidad */
    public static final String UTILITY_EXCEPTION_TYPE = "exceptionType";
    public static final String UTILITY_EXCEPTION_MESSAGE = "exceptionMessage";
    public static final String UTILITY_KEY_VALUE_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "The value has an incorrect format for the field:";
    public static final String UTILITY_KEY_NAME_EXCEPTION_TYPE = "exceptionType";
    public static final String UTILITY_KEY_NAME_EXCEPTION_MESSAGE = "exceptionMessage";
    public static final String UTILITY_STRING_FORMAT_INCORRECT = "The field = %s has value = %s with incorrect format";

    /*  Constantes de errores */
    public static final String EXCEPTION_RESOURCE_FOUND_EXCEPTION = "Incorrect format in the URL";
    public static final String EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION = "Bad Request - The value of one or more input parameters is invalid. Please review the submitted data.";
    public static final String EXCEPTION_METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "Bad Request - The data in the request body does not comply with the required format or restrictions. Please check the required fields or permitted values.";
    public static final String EXCEPTION_METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION = "Bad Request - The provided data type is invalid for one of the request parameters. Verify that each value sent matches the expected type.";
    public static final String EXCEPTION_CONSTRAINT_VIOLATION_EXCEPTION = "Bad Request - One or more of the supplied parameters do not meet the defined constraints.";
    public static final String EXCEPTION_MISSING_REQUEST_PARAMETER_EXCEPTION = "Bad Request - A required parameter is missing from the request. Please ensure you include all required parameters, such as 'applicationDate', 'productId', or 'brandId'.";
    public static final String EXCEPTION_MISSING_REQUEST_HEADER_EXCEPTION = "Bad Request - One of the required headers is missing from the request. Make sure to include all necessary headers, such as 'flowId'.";
    public static final String EXCEPTION_NOT_RESOURCE_FOUND_EXCEPTION = "Not Found - No resource was found matching the provided URL. Please check the submitted URL.";
    public static final String EXCEPTION_NOT_FOUND_EXCEPTION = "Not Found - No prices were found for the given parameters: application date, product ID, and brand ID.";
    public static final String EXCEPTION_GENERIC = "Internal Server Error - An unexpected error occurred while processing your request. Please try again later or contact support.";

}

package com.smo.price.infrastructure.swagger.examples;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiErrorDataResponsesExamples {

    public static final String ERROR_400_EXAMPLE = """
            {
                "statusCode": "400",
                "status": "ERROR",
                "message": "Bad Request - Invalid request data or missing required parameters.",
                "data": {
                    "errorDetails": {
                        "code": "400 BAD_REQUEST",
                        "fields": {
                            "exceptionType": "ApiException",
                            "exceptionMessage": "Invalid request data or missing required parameters: + parametersError"
                        }
                    }
                },
                "timestamp": "2025-02-17T09:23:28.4780715-05:00",
                "flowId": "123"
            }
            """;

    public static final String ERROR_401_EXAMPLE = """
            {
                "statusCode": "401",
                "status": "ERROR",
                "message": "Unauthorized - Missing or invalid credentials, or expired token.",
                "data": {
                    "errorDetails": {
                        "code": "401 UNAUTHORIZED",
                        "fields": {
                            "exceptionType": "ApiException",
                            "exceptionMessage": "Unauthorized. Invalid or missing access token."
                        }
                    }
                },
                "timestamp": "2025-02-17T09:24:28.4780715-05:00",
                "flowId": "456"
            }
            """;

    public static final String ERROR_403_EXAMPLE = """
            {
                "statusCode": "403",
                "status": "ERROR",
                "message": "Forbidden - Insufficient permissions for authenticity evaluation.",
                "data": {
                    "errorDetails": {
                        "code": "403 FORBIDDEN",
                        "fields": {
                            "exceptionType": "ApiException",
                            "exceptionMessage": "Access denied. You do not have the necessary permissions."
                        }
                    }
                },
                "timestamp": "2025-02-17T09:25:28.4780715-05:00",
                "flowId": "789"
            }
            """;

    public static final String ERROR_404_EXAMPLE = """
            {
                "statusCode": "404",
                "status": "ERROR",
                "message": "Not Found - No data found for the data provided.",
                "data": {
                    "errorDetails": {
                        "code": "404 NOT_FOUND",
                        "fields": {
                            "exceptionType": "ApiException",
                            "exceptionMessage": "The data was not found."
                        }
                    }
                },
                "timestamp": "2025-02-17T09:26:28.4780715-05:00",
                "flowId": "321"
            }
            """;

    public static final String ERROR_408_EXAMPLE = """
            {
                "statusCode": "408",
                "status": "ERROR",
                "message": "Request Timeout - The request exceeded the configured timeout.",
                "data": {
                    "errorDetails": {
                        "code": "408 REQUEST_TIMEOUT",
                        "fields": {
                            "exceptionType": "ApiException",
                            "exceptionMessage": "Timeout. Please try again."
                        }
                    }
                },
                "timestamp": "2025-02-17T09:27:28.4780715-05:00",
                "flowId": "654"
            }
            """;

    public static final String ERROR_409_EXAMPLE = """
            {
                "statusCode": "409",
                "status": "ERROR",
                "message": "Conflict - Request conflicts with the current resource state (e.g., duplicate creation).",
                "data": {
                    "errorDetails": {
                        "code": "409 CONFLICT",
                        "fields": {
                            "exceptionType": "ApiException",
                            "exceptionMessage": "Conflict detected in the processing of the request."
                        }
                    }
                },
                "timestamp": "2025-02-17T09:28:28.4780715-05:00",
                "flowId": "987"
            }
            """;

    public static final String ERROR_500_EXAMPLE = """
            {
                "statusCode": "500",
                "status": "ERROR",
                "message": "Internal Server Error - Unexpected server error preventing request completion.",
                "data": {
                    "errorDetails": {
                        "code": "500 INTERNAL_SERVER_ERROR",
                        "fields": {
                            "exceptionType": "ApiException",
                            "exceptionMessage": "Internal server error. Please try again later."
                        }
                    }
                },
                "timestamp": "2025-02-17T09:29:28.4780715-05:00",
                "flowId": "741"
            }
            """;

    public static final String ERROR_504_EXAMPLE = """
            {
                "statusCode": "504",
                "status": "ERROR",
                "message": "Gateway Timeout - Gateway timed out at the limit set in Service.",
                "data": {
                    "errorDetails": {
                        "code": "504 GATEWAY_TIMEOUT",
                        "fields": {
                            "exceptionType": "ApiException",
                            "exceptionMessage": "Network gateway timed out."
                        }
                    }
                },
                "timestamp": "2025-02-17T09:30:28.4780715-05:00",
                "flowId": "852"
            }
            """;
}
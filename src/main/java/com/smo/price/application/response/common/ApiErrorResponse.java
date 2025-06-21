package com.smo.price.application.response.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Map;


@Getter
@Setter
@Schema(description = "Standard error response structure.")
public class ApiErrorResponse {

    @Schema(description = "HTTP status code as string", example = "400")
    private String statusCode;
    @Schema(description = "Status of the response", example = "ERROR")
    private String status;
    @Schema(description = "Human-readable error message", example = "Field X is required")
    private String message;
    @Schema(description = "Wrapper for error details.")
    private ErrorData data;
    @Schema(description = "Timestamp of the error in ISO format", example = "2025-05-17T12:34:56")
    private ZonedDateTime timestamp;
    @Schema(description = "Flow ID for traceability", example = "f9d3-789f-123f-888f")
    private String flowId;

    public ApiErrorResponse(String statusCode, String message, String flowId, ErrorDetails errorDetails) {
        this.statusCode = statusCode;
        this.status = "ERROR";
        this.message = message;
        this.timestamp = ZonedDateTime.now();
        this.flowId = flowId;
        this.data = new ErrorData(errorDetails);
    }

    @Getter
    @Setter
    @Schema(description = "Wrapper object for the error details section.")
    public static class ErrorData {
        @Schema(description = "Error detail information.")
        private ErrorDetails errorDetails;

        public ErrorData(ErrorDetails errorDetails) {
            this.errorDetails = errorDetails;
        }
    }

    @Getter
    @Setter
    @Schema(description = "Detailed error information.")
    public static class ErrorDetails {
        @Schema(description = "Internal error code identifier", example = "EXCEPTION_ERROR")
        private String code;
        @Schema(description = "Map of fields with related error messages.")
        private Map<String, String> fields;

        public ErrorDetails(String code, Map<String, String> fields) {
            this.code = code;
            this.fields = fields;
        }
    }
}
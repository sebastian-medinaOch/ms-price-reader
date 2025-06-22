package com.smo.price.application.response.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Schema(description = "Generic structure for successful API responses.")
public class ApiDataResponse<T> {

    @Schema(description = "HTTP status code as string", example = "200")
    private String statusCode;
    @Schema(description = "Status indicator of the response", example = "SUCCESS")
    private String status;
    @Schema(description = "Response message", example = "Successful consultation.")
    private String message;
    @Schema(description = "Data returned by the API")
    private T data;
    @Schema(description = "Timestamp of the response", example = "2025-05-17T15:03:00")
    private ZonedDateTime timestamp;
    @Schema(description = "Transaction identifier for traceability", example = "abcd1234")
    private String flowId;


    public ApiDataResponse(String statusCode, String message, T data, String flowId) {
        this.statusCode = statusCode;
        this.status = "SUCCESS";
        this.message = message;
        this.data = data;
        this.timestamp = ZonedDateTime.now();
        this.flowId = flowId;
    }
}
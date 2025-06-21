package com.smo.price.infrastructure.utility;

import com.smo.price.application.response.common.ApiErrorResponse;
import com.smo.price.application.response.common.ApiDataResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseFactory {

    public static <T> ApiDataResponse<T> createSuccessResponse(HttpStatus status, String message, T data, String flowId) {
        return new ApiDataResponse<>(String.valueOf(status.value()), message, data, flowId);
    }

    public static ResponseEntity<ApiErrorResponse> createErrorResponse(HttpStatus status, String message,
                                                                       String flowId,
                                                                       ApiErrorResponse.ErrorDetails details) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                String.valueOf(status.value()),
                message,
                flowId,
                details
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}

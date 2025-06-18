package com.smo.price.infrastructure.utility;

import com.smo.price.application.response.common.ApiErrorResponse;
import com.smo.price.application.response.common.ApiResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseFactory {

    public static <T> ApiResponse<T> createSuccessResponse(HttpStatus status, String message, T data, String transactionId) {
        return new ApiResponse<>(String.valueOf(status.value()), message, data, transactionId);
    }

    public static ResponseEntity<ApiErrorResponse> createErrorResponse(HttpStatus status, String message,
                                                                       String transactionId,
                                                                       ApiErrorResponse.ErrorDetails details) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                String.valueOf(status.value()),
                message,
                transactionId,
                details
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}

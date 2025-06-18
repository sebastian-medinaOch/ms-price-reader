package com.smo.price.infrastructure.exception.handler;

import com.smo.price.application.response.common.ApiErrorResponse;
import com.smo.price.infrastructure.exception.errors.ApiException;
import com.smo.price.infrastructure.utility.ExceptionFieldMapper;
import com.smo.price.infrastructure.utility.ResponseFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

import static com.smo.price.infrastructure.commons.ExceptionsConstants.EXCEPTION_GENERIC;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.EXCEPTION_METHOD_ARGUMENT_NOT_VALID_EXCEPTION;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.EXCEPTION_MISSING_REQUEST_HEADER_EXCEPTION;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.EXCEPTION_MISSING_REQUEST_PARAMETER_EXCEPTION;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.EXCEPTION_NOT_RESOURCE_FOUND_EXCEPTION;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.HEADER_FLOW_ID;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.LOG_EXCEPTION_MESSAGE;


@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(ApiException exception, WebRequest request) {
        return logAndBuildResponse(exception.getHttpStatus(), exception.getMessageLog(), request,
                ExceptionFieldMapper.fromApiException(exception), exception.getMessageResponse());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        return logAndBuildResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request,
                ExceptionFieldMapper.fromIllegalArgumentException(exception), EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(MethodArgumentNotValidException exception, WebRequest request) {
        return logAndBuildResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request,
                ExceptionFieldMapper.fromValidationException(exception), EXCEPTION_METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(MissingServletRequestParameterException exception, WebRequest request) {
        return logAndBuildResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request,
                ExceptionFieldMapper.fromMissingRequestParameter(exception), EXCEPTION_MISSING_REQUEST_PARAMETER_EXCEPTION);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(MissingRequestHeaderException exception, WebRequest request) {
        return logAndBuildResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request,
                ExceptionFieldMapper.fromMissingHeaderException(exception), EXCEPTION_MISSING_REQUEST_HEADER_EXCEPTION);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNoResourceFoundException(NoResourceFoundException exception, WebRequest request) {
        return logAndBuildResponse(HttpStatus.NOT_FOUND, exception.getBody().getDetail(), request,
                ExceptionFieldMapper.fromNoResourceFoundException(exception), EXCEPTION_NOT_RESOURCE_FOUND_EXCEPTION);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception exception, WebRequest request) {
        return logAndBuildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), request,
                ExceptionFieldMapper.fromGenericException(exception), EXCEPTION_GENERIC);
    }

    private String getFlowId(WebRequest request) {
        return request.getHeader(HEADER_FLOW_ID);
    }

    private ResponseEntity<ApiErrorResponse> logAndBuildResponse(HttpStatus status, String errorDetail, WebRequest request,
                                                                 Map<String, String> fields, String messageResponse) {
        String flowId = getFlowId(request);
        log.error(LOG_EXCEPTION_MESSAGE, flowId, errorDetail);
        ApiErrorResponse.ErrorDetails details = new ApiErrorResponse.ErrorDetails(String.valueOf(status.value()), fields);
        return ResponseFactory.createErrorResponse(status, messageResponse, flowId, details);
    }

}

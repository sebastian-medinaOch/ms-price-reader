package com.smo.price.infrastructure.exception.handler;

import com.smo.price.application.response.common.ApiErrorResponse;
import com.smo.price.infrastructure.exception.errors.ApiException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void handleApiException_ShouldReturnErrorResponse() {
        String flowId = "test-flow-id";
        ApiException exception = new ApiException(HttpStatus.NOT_FOUND, "Price not found", "NOT_FOUND", "Price not found for given parameters");

        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleApiException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("NOT_FOUND", result.getBody().getMessage());
        verify(webRequest).getHeader("flowId");
    }

    @Test
    void handleIllegalArgumentException_ShouldReturnBadRequest() {
        String flowId = "test-flow-id";
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");

        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleIllegalArgumentException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }

    @Test
    void handleConstraintViolationException_ShouldReturnBadRequest() {
        String flowId = "test-flow-id";
        ConstraintViolationException exception = mock(ConstraintViolationException.class);

        when(exception.getMessage()).thenReturn("Validation failed");
        when(exception.getConstraintViolations()).thenReturn(Set.of());
        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleConstraintViolationException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }


    @Test
    void handleDataAccessException_ShouldReturnInternalServerError() {
        String flowId = "test-flow-id";
        DataAccessException exception = new DataAccessException("Database error") {
        };

        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleDataAccessException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }

    @Test
    void handleGeneralException_ShouldReturnInternalServerError() {
        String flowId = "test-flow-id";
        Exception exception = new Exception("Generic error");

        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleGeneralException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }

    @Test
    void handleMethodArgumentNotValidException_ShouldReturnBadRequest() {
        String flowId = "test-flow-id";
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        ObjectError objectError = mock(ObjectError.class);

        when(exception.getMessage()).thenReturn("Validation failed");
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(objectError));
        when(objectError.getDefaultMessage()).thenReturn("Field is required");
        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleMethodArgumentNotValidException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }

    @Test
    void handleMethodArgumentTypeMismatchException_ShouldReturnBadRequest() {
        String flowId = "test-flow-id";
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);

        when(exception.getName()).thenReturn("productId");
        when(exception.getValue()).thenReturn("invalid");
        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleMethodArgumentTypeMismatchException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }

    @Test
    void handleMissingServletRequestParameterException_ShouldReturnBadRequest() {
        String flowId = "test-flow-id";
        MissingServletRequestParameterException exception = mock(MissingServletRequestParameterException.class);

        when(exception.getMessage()).thenReturn("Required request parameter 'productId' is not present");
        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleMissingServletRequestParameterException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }

    @Test
    void handleMissingRequestHeaderException_ShouldReturnBadRequest() {
        String flowId = "test-flow-id";
        MissingRequestHeaderException exception = mock(MissingRequestHeaderException.class);
        ProblemDetail problemDetail = mock(ProblemDetail.class);

        when(exception.getMessage()).thenReturn("Required request header 'flowId' is not present");
        when(exception.getBody()).thenReturn(problemDetail);
        when(problemDetail.getDetail()).thenReturn("Required request header 'flowId' is not present");
        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleMissingRequestHeaderException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }

    @Test
    void handleNoResourceFoundException_ShouldReturnNotFound() {
        String flowId = "test-flow-id";
        NoResourceFoundException exception = mock(NoResourceFoundException.class);
        ProblemDetail problemDetail = mock(ProblemDetail.class);

        when(exception.getBody()).thenReturn(problemDetail);
        when(problemDetail.getDetail()).thenReturn("No static resource found");
        when(webRequest.getHeader("flowId")).thenReturn(flowId);

        ResponseEntity<ApiErrorResponse> result = globalExceptionHandler.handleNoResourceFoundException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(webRequest).getHeader("flowId");
    }

}
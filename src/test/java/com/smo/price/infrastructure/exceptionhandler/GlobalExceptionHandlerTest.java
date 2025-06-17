package com.smo.price.infrastructure.exceptionhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.REQUEST_HEADER_MESSAGE_ID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private ServerWebExchange exchange;
    @Mock
    private ServerHttpRequest mockRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        HttpHeaders headers = new HttpHeaders();
        headers.add(REQUEST_HEADER_MESSAGE_ID, "test-message-id");

        when(exchange.getRequest()).thenReturn(mock(ServerHttpRequest.class));
        when(mockRequest.getHeaders()).thenReturn(headers);
        when(exchange.getRequest()).thenReturn(mockRequest);
    }

    @Test
    void handleBussinessException() {

        BussinessException exception = new BussinessException(HttpStatus.BAD_REQUEST, "Error de negocio");

        Mono<ResponseEntity<Mono<AnswerException>>> result = globalExceptionHandler.handleBussinessException(exception, exchange);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
                    assert Objects.requireNonNull(response.getBody()).subscribe(answer -> answer.getMessage().equals("Error de negocio")).isDisposed();
                })
                .verifyComplete();
    }

    @Test
    void handleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Parámetro inválido");

        Mono<ResponseEntity<Mono<AnswerException>>> result = globalExceptionHandler.handleIllegalArgumentException(exception, exchange);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
                    assert Objects.requireNonNull(response.getBody()).subscribe(answer -> answer.getMessage().equals("Parámetro inválido")).isDisposed();
                })
                .verifyComplete();
    }

    @Test
    void handleNoResourceFoundException() {
        NoResourceFoundException exception = new NoResourceFoundException("Recurso no encontrado");

        Mono<ResponseEntity<Mono<AnswerException>>> result = globalExceptionHandler.handleNoResourceFoundException(exception, exchange);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assert response.getStatusCode() == HttpStatus.NOT_FOUND;
                    assert Objects.requireNonNull(response.getBody()).subscribe(answer -> answer.getMessage().equals("Recurso no encontrado")).isDisposed();
                })
                .verifyComplete();
    }

    @Test
    void handleResponseStatusException() {
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autorizado");

        Mono<ResponseEntity<Mono<AnswerException>>> result = globalExceptionHandler.handleResponseStatusException(exception, exchange);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assert response.getStatusCode() == HttpStatus.UNAUTHORIZED;
                    assert Objects.requireNonNull(response.getBody()).subscribe(answer -> answer.getMessage().equals("No autorizado")).isDisposed();
                })
                .verifyComplete();
    }

    @Test
    void handleGeneralException() {
        Exception exception = new Exception("Error inesperado");

        Mono<ResponseEntity<Mono<AnswerException>>> result = globalExceptionHandler.handleGeneralException(exception, exchange);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assert response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
                    assert Objects.requireNonNull(response.getBody()).subscribe(answer -> answer.getMessage().equals("Error inesperado")).isDisposed();
                })
                .verifyComplete();
    }

}
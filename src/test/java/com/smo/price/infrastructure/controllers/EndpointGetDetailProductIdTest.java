package com.smo.price.infrastructure.controllers;

import com.smo.price.infrastructure.commons.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.PATH_GET_DETAIL_PRODUCT_ID_CONTROLLER;
import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.PATH_PRODUCT_CONTROLLER;
import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.REQUEST_HEADER_MESSAGE_ID;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EndpointGetDetailProductIdTest {

    @InjectMocks
    private EndpointGetDetailProductId endpointGetDetailProductId;

    @Mock
    private IGetDetailProductId iGetDetailProductId;
    @Mock
    private Utility utility;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(endpointGetDetailProductId).build();
    }

    @Test
    void getDetailProductIdSuccess() {

        String productId = "123";
        String messageId = "msg-456";
        Object mockResponse = "Product Detail";

        when(utility.validateData(productId, messageId)).thenReturn(Mono.empty());
        when(iGetDetailProductId.get(productId)).thenReturn(Mono.just(mockResponse));

        Mono<Object> result = endpointGetDetailProductId.getDetailProductId(productId, messageId);

        StepVerifier.create(result)
                .expectNext(mockResponse)
                .verifyComplete();

        verify(utility, times(1)).validateData(productId, messageId);
        verify(iGetDetailProductId, times(1)).get(productId);

    }

    @Test
    void getDetailProductIdFails() {

        String productId = "123";
        String messageId = "msg-456";

        when(utility.validateData(productId, messageId)).thenReturn(Mono.empty());
        when(iGetDetailProductId.get(productId)).thenReturn(Mono.error(new RuntimeException("Service failure")));


        webTestClient.get()
                .uri(PATH_PRODUCT_CONTROLLER + PATH_GET_DETAIL_PRODUCT_ID_CONTROLLER, productId)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .header(REQUEST_HEADER_MESSAGE_ID, messageId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        verify(utility, times(1)).validateData(productId, messageId);
        verify(iGetDetailProductId, times(1)).get(productId);

    }

}
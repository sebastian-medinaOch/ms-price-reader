package com.smo.price.infrastructure.controllers;

import com.smo.price.infrastructure.commons.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.PATH_GET_SIMILAR_PRODUCTS_IDS_CONTROLLER;
import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.PATH_PRODUCT_CONTROLLER;
import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.REQUEST_HEADER_MESSAGE_ID;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EndpointGetSimilarProductsIdsTest {

    @InjectMocks
    private EndpointGetSimilarProductsIds endpointGetSimilarProductsIds;

    @Mock
    private IGetSimilarProductsIds iGetSimilarProductsIds;
    @Mock
    private Utility utility;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(endpointGetSimilarProductsIds).build();
    }

    @Test
    void getSimilarProductsIdsSuccess() {
        String productId = "123";
        String messageId = "msg-456";
        Object mockResponse = "Similar Product 1";

        when(utility.validateData(productId, messageId)).thenReturn(Mono.empty());
        when(iGetSimilarProductsIds.get(productId)).thenReturn(Flux.just(mockResponse));

        Flux<Object> result = endpointGetSimilarProductsIds.getSimilarProductsIds(productId, messageId);

        StepVerifier.create(result)
                .expectNext(mockResponse)
                .verifyComplete();

        verify(utility, times(1)).validateData(productId, messageId);
        verify(iGetSimilarProductsIds, times(1)).get(productId);
    }

    @Test
    void getSimilarProductsIdsFailed() {
        String productId = "123";
        String messageId = "msg-456";

        when(utility.validateData(productId, messageId)).thenReturn(Mono.empty());
        when(iGetSimilarProductsIds.get(productId)).thenReturn(Flux.error(new RuntimeException("Service failure")));

        webTestClient.get()
                .uri(PATH_PRODUCT_CONTROLLER + PATH_GET_SIMILAR_PRODUCTS_IDS_CONTROLLER, productId)
                .header(REQUEST_HEADER_MESSAGE_ID, messageId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
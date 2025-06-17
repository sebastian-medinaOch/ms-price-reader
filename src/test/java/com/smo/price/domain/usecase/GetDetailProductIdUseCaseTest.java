package com.smo.price.domain.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDetailProductIdUseCaseTest {

    @InjectMocks
    private GetDetailProductIdUseCase getDetailProductIdUseCase;

    @Mock
    private IProductRestOn iProductRestOn;


    private final String testProductId = "123";

    @Test
    void getSuccess() {
        ProductResponseModel mockProductData = ProductResponseModel.builder().id("123").build();
        DataResponseModel mockResponse = DataResponseModel.builder().data(mockProductData).build();

        when(iProductRestOn.getProduct(testProductId)).thenReturn(Mono.just(mockProductData));

        StepVerifier.create(getDetailProductIdUseCase.get(testProductId))
                .expectNext(mockResponse)
                .verifyComplete();

        verify(iProductRestOn, times(1)).getProduct(testProductId);

    }

    @Test
    void getDataEmpty() {
        when(iProductRestOn.getProduct(testProductId)).thenReturn(Mono.empty());

        StepVerifier.create(getDetailProductIdUseCase.get(testProductId))
                .verifyComplete();

        verify(iProductRestOn, times(1)).getProduct(testProductId);

    }

}
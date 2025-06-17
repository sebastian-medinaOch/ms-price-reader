package com.smo.price.application.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSimilarProductsIdsServiceTest {

    @InjectMocks
    private GetSimilarProductsIdsService getSimilarProductsIdsService;

    @Mock
    private IGetSimilarProductsIdsUseCaseIn iGetSimilarProductsIdsUseCaseIn;
    @Mock
    private ObjectMapper objectMapper;


    private final String testProductId = "123";

    @Test
    void getSuccess() {
        DataResponse mockResponse = new DataResponse("Mocked Data");
        DataResponseModel mockProductData = DataResponseModel.builder().data("data").build();

        when(iGetSimilarProductsIdsUseCaseIn.get(testProductId)).thenReturn(Flux.just(mockProductData));
        when(objectMapper.convertValue(mockProductData, DataResponse.class)).thenReturn(mockResponse);

        StepVerifier.create(getSimilarProductsIdsService.get(testProductId))
                .expectNext(mockResponse.getData())
                .verifyComplete();

        verify(iGetSimilarProductsIdsUseCaseIn, times(1)).get(testProductId);
        verify(objectMapper, times(1)).convertValue(mockProductData, DataResponse.class);
    }

    @Test
    void getDataEmpty() {
        when(iGetSimilarProductsIdsUseCaseIn.get(testProductId)).thenReturn(Flux.empty());

        StepVerifier.create(getSimilarProductsIdsService.get(testProductId))
                .verifyComplete();

        verify(iGetSimilarProductsIdsUseCaseIn, times(1)).get(testProductId);
        verify(objectMapper, never()).convertValue(any(), eq(DataResponse.class));
    }

}
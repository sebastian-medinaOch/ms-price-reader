package com.smo.price.domain.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSimilarProductsIdsUseCaseTest {

    @InjectMocks
    private GetSimilarProductsIdsUseCase getSimilarProductsIdsUseCase;

    @Mock
    private IProductRestOn iProductRestOn;

    private final String testProductId = "123";

    @Test
    void getSuccess() {
        List<Integer> mockProductIds = List.of(2, 3, 4);
        DataResponseModel expectedResponse1 = DataResponseModel.builder().data(2).build();
        DataResponseModel expectedResponse2 = DataResponseModel.builder().data(3).build();
        DataResponseModel expectedResponse3 = DataResponseModel.builder().data(4).build();

        when(iProductRestOn.getProducts(testProductId)).thenReturn(Flux.fromIterable(mockProductIds));

        StepVerifier.create(getSimilarProductsIdsUseCase.get(testProductId))
                .expectNext(expectedResponse1)
                .expectNext(expectedResponse2)
                .expectNext(expectedResponse3)
                .verifyComplete();

        verify(iProductRestOn, times(1)).getProducts(testProductId);
    }

    @Test
    void getDataEmpty() {
        when(iProductRestOn.getProducts(testProductId)).thenReturn(Flux.empty());

        StepVerifier.create(getSimilarProductsIdsUseCase.get(testProductId))
                .verifyComplete();

        verify(iProductRestOn, times(1)).getProducts(testProductId);
    }

}
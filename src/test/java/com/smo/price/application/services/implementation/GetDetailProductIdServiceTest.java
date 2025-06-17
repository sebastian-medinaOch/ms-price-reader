package com.smo.price.application.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDetailProductIdServiceTest {

    @InjectMocks
    private GetDetailProductIdService getDetailProductIdService;

    @Mock
    private IGetDetailProductIdUseCaseIn iGetDetailProductIdUseCaseIn;
    @Mock
    private ObjectMapper objectMapper;

    private final String testProductId = "123";

    @Test
    void getSuccess() {
        DataResponse mockResponse = new DataResponse("Mocked Data");
        Object mockProductData = "Mocked Data";

        when(iGetDetailProductIdUseCaseIn.get(testProductId)).thenReturn(Mono.just(mockProductData));
        when(objectMapper.convertValue(mockProductData, DataResponse.class)).thenReturn(mockResponse);

        StepVerifier.create(getDetailProductIdService.get(testProductId))
                .expectNext(mockResponse.getData())
                .verifyComplete();

        verify(iGetDetailProductIdUseCaseIn, times(1)).get(testProductId);
        verify(objectMapper, times(1)).convertValue(mockProductData, DataResponse.class);

    }

    @Test
    void getDataEmpty() {
        when(iGetDetailProductIdUseCaseIn.get(testProductId)).thenReturn(Mono.empty());

        StepVerifier.create(getDetailProductIdService.get(testProductId))
                .verifyComplete();

        verify(iGetDetailProductIdUseCaseIn, times(1)).get(testProductId);
        verify(objectMapper, never()).convertValue(any(), eq(DataResponse.class));
    }

}
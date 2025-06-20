package com.smo.price.application.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.price.application.response.price.PriceResponse;
import com.smo.price.domain.models.response.PriceResponseModel;
import com.smo.price.domain.ports.in.IGetPriceUseCaseIn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPriceServiceTest {

    @Mock
    private IGetPriceUseCaseIn iGetPriceUseCaseIn;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GetPriceService getPriceService;

    @Test
    void getPrice_ShouldReturnPriceResponse() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        String flowId = "test-flow-id";

        PriceResponseModel mockPrice = PriceResponseModel.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(applicationDate)
                .endDate(applicationDate.plusDays(1))
                .price(BigDecimal.valueOf(35.50))
                .build();

        PriceResponse expectedResponse = PriceResponse.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(applicationDate)
                .endDate(applicationDate.plusDays(1))
                .price(BigDecimal.valueOf(35.50))
                .build();

        when(iGetPriceUseCaseIn.getPrice(applicationDate, productId, brandId, flowId))
                .thenReturn(mockPrice);
        when(objectMapper.convertValue(mockPrice, PriceResponse.class))
                .thenReturn(expectedResponse);

        PriceResponse result = getPriceService.getPrice(applicationDate, productId, brandId, flowId);

        assertEquals(expectedResponse, result);
        verify(iGetPriceUseCaseIn).getPrice(applicationDate, productId, brandId, flowId);
        verify(objectMapper).convertValue(mockPrice, PriceResponse.class);
    }
}
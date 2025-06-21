package com.smo.price.infrastructure.controllers;

import com.smo.price.application.response.common.ApiDataResponse;
import com.smo.price.application.response.price.PriceResponse;
import com.smo.price.application.services.interfaces.IGetPrice;
import com.smo.price.infrastructure.controllers.price.PriceController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private IGetPrice iGetPrice;

    @InjectMocks
    private PriceController priceController;

    @Test
    void getPrice_ShouldReturnSuccessResponse() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        String flowId = "test-flow-id";

        PriceResponse mockResponse = PriceResponse.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(applicationDate)
                .endDate(applicationDate.plusDays(1))
                .price(BigDecimal.valueOf(35.50))
                .build();

        when(iGetPrice.getPrice(applicationDate, productId, brandId, flowId))
                .thenReturn(mockResponse);

        ResponseEntity<ApiDataResponse<PriceResponse>> result =
                priceController.getPrice(applicationDate, productId, brandId, flowId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockResponse, result.getBody().getData());
        verify(iGetPrice).getPrice(applicationDate, productId, brandId, flowId);
    }
}
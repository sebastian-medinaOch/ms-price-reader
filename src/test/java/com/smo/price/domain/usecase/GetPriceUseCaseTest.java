package com.smo.price.domain.usecase;

import com.smo.price.domain.models.response.PriceResponseModel;
import com.smo.price.domain.ports.out.IGetPriceOut;
import com.smo.price.infrastructure.exception.errors.ApiException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseTest {

    @Mock
    private IGetPriceOut iGetPriceOut;

    @InjectMocks
    private GetPriceUseCase getPriceUseCase;

    @Test
    void getPrice_ShouldReturnPriceResponseModelWithHighestPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        String flowId = "test-flow-id";

        PriceResponseModel lowPriorityPrice = PriceResponseModel.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(applicationDate)
                .endDate(applicationDate.plusDays(1))
                .price(BigDecimal.valueOf(35.50))
                .priority(0)
                .build();

        PriceResponseModel highPriorityPrice = PriceResponseModel.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(2)
                .startDate(applicationDate)
                .endDate(applicationDate.plusDays(1))
                .price(BigDecimal.valueOf(25.45))
                .priority(1)
                .build();

        List<PriceResponseModel> prices = Arrays.asList(lowPriorityPrice, highPriorityPrice);

        when(iGetPriceOut.getPrice(applicationDate, productId, brandId, flowId))
                .thenReturn(prices);

        PriceResponseModel result = getPriceUseCase.getPrice(applicationDate, productId, brandId, flowId);

        assertEquals(highPriorityPrice, result);
        verify(iGetPriceOut).getPrice(applicationDate, productId, brandId, flowId);
    }

    @Test
    void getPrice_WhenPriceNotFound_ShouldThrowApiException() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        String flowId = "test-flow-id";

        when(iGetPriceOut.getPrice(applicationDate, productId, brandId, flowId))
                .thenReturn(Collections.emptyList());

        ApiException exception = assertThrows(ApiException.class,
                () -> getPriceUseCase.getPrice(applicationDate, productId, brandId, flowId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        verify(iGetPriceOut).getPrice(applicationDate, productId, brandId, flowId);
    }
}
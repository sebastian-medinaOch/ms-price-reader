package com.smo.price.infrastructure.dataproviders.database.implementation;

import com.smo.price.domain.models.response.PriceResponseModel;
import com.smo.price.infrastructure.dataproviders.database.entities.PriceEntity;
import com.smo.price.infrastructure.dataproviders.database.repository.IPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceDatabaseAdapterTest {

    @Mock
    private IPriceRepository priceRepository;

    @InjectMocks
    private PriceDatabaseAdapter priceDatabaseAdapter;

    @Test
    void getPrice_ShouldReturnPriceResponseModel() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        String flowId = "test-flow-id";

        PriceEntity mockEntity = PriceEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .priceList(1)
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .price(BigDecimal.valueOf(35.50))
                .build();

        when(priceRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate))
                .thenReturn(Optional.of(mockEntity));

        PriceResponseModel result = priceDatabaseAdapter.getPrice(applicationDate, productId, brandId, flowId);

        assertNotNull(result);
        assertEquals(brandId, result.getBrandId());
        assertEquals(productId, result.getProductId());
        assertEquals(1, result.getPriceList());
        assertEquals(BigDecimal.valueOf(35.50), result.getPrice());
        verify(priceRepository).findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate);
    }

    @Test
    void getPrice_WhenNotFound_ShouldReturnNull() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        String flowId = "test-flow-id";

        when(priceRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate))
                .thenReturn(Optional.empty());

        PriceResponseModel result = priceDatabaseAdapter.getPrice(applicationDate, productId, brandId, flowId);

        assertNull(result);
        verify(priceRepository).findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate);
    }
}
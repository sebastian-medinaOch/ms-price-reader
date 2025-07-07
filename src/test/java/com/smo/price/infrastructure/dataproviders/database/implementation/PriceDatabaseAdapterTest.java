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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceDatabaseAdapterTest {

    @Mock
    private IPriceRepository priceRepository;

    @InjectMocks
    private PriceDatabaseAdapter priceDatabaseAdapter;

    @Test
    void getPrice_ShouldReturnListOfPriceResponseModels() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        String flowId = "test-flow-id";

        PriceEntity priceEntity1 = PriceEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .priceList(1)
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .price(BigDecimal.valueOf(35.50))
                .priority(0)
                .build();

        PriceEntity priceEntity2 = PriceEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .priceList(2)
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .price(BigDecimal.valueOf(25.45))
                .priority(1)
                .build();

        List<PriceEntity> priceEntities = Arrays.asList(priceEntity1, priceEntity2);

        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                brandId, productId, applicationDate, applicationDate))
                .thenReturn(priceEntities);

        List<PriceResponseModel> result = priceDatabaseAdapter.getPrice(applicationDate, productId, brandId, flowId);

        assertNotNull(result);
        assertEquals(2, result.size());

        PriceResponseModel firstPrice = result.get(0);
        assertEquals(brandId, firstPrice.getBrandId());
        assertEquals(productId, firstPrice.getProductId());
        assertEquals(1, firstPrice.getPriceList());
        assertEquals(BigDecimal.valueOf(35.50), firstPrice.getPrice());

        verify(priceRepository).findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                brandId, productId, applicationDate, applicationDate);
    }

    @Test
    void getPrice_WhenNotFound_ShouldReturnEmptyList() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        String flowId = "test-flow-id";

        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                brandId, productId, applicationDate, applicationDate))
                .thenReturn(Collections.emptyList());

        List<PriceResponseModel> result = priceDatabaseAdapter.getPrice(applicationDate, productId, brandId, flowId);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(priceRepository).findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                brandId, productId, applicationDate, applicationDate);
    }
}
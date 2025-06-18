package com.smo.price.infrastructure.dataproviders.database.implementation;

import com.smo.price.domain.ports.out.IGetPriceOut;
import com.smo.price.infrastructure.dataproviders.database.entities.PriceEntity;
import com.smo.price.infrastructure.dataproviders.database.repository.IPriceRepository;
import com.smo.price.infrastructure.dataproviders.database.response.PriceResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class GetPriceDataBase implements IGetPriceOut {

    private final IPriceRepository priceRepository;

    @Override
    public PriceResponseEntity getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId) {
        return priceRepository
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId, productId, applicationDate, applicationDate)
                .map(this::buildPriceResponseEntity)
                .orElse(null);
    }

    private PriceResponseEntity buildPriceResponseEntity(PriceEntity price) {
        return PriceResponseEntity.builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .build();
    }

}

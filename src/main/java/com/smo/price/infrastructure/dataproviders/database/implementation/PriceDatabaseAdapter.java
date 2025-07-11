package com.smo.price.infrastructure.dataproviders.database.implementation;

import com.smo.price.domain.models.response.PriceResponseModel;
import com.smo.price.domain.ports.IGetPriceOut;
import com.smo.price.infrastructure.dataproviders.database.entities.PriceEntity;
import com.smo.price.infrastructure.dataproviders.database.repository.IPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.smo.price.infrastructure.commons.InfrastructureConstants.LOG_PRODUCT_CONSULTED;

@RequiredArgsConstructor
@Service
@Log4j2
public class PriceDatabaseAdapter implements IGetPriceOut {

    private final IPriceRepository priceRepository;

    @Override
    public List<PriceResponseModel> getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId) {

        log.debug(LOG_PRODUCT_CONSULTED,
                flowId, productId, brandId, applicationDate);

        return priceRepository
                .findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                        brandId, productId, applicationDate, applicationDate)
                .stream()
                .map(this::mapToDomainModel)
                .toList();
    }

    private PriceResponseModel mapToDomainModel(PriceEntity price) {
        return PriceResponseModel.builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priority(price.getPriority())
                .price(price.getPrice())
                .build();
    }

}

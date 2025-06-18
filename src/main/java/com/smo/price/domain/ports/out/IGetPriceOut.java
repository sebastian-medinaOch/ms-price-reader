package com.smo.price.domain.ports.out;

import com.smo.price.infrastructure.dataproviders.database.response.PriceResponseEntity;

import java.time.LocalDateTime;

public interface IGetPriceOut {

    PriceResponseEntity getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId);

}

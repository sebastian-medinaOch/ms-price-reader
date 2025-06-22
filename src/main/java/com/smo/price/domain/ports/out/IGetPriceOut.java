package com.smo.price.domain.ports.out;

import com.smo.price.domain.models.response.PriceResponseModel;

import java.time.LocalDateTime;

public interface IGetPriceOut {

    PriceResponseModel getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId);

}

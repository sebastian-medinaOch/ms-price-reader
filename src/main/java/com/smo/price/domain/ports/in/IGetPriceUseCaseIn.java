package com.smo.price.domain.ports.in;

import com.smo.price.domain.models.response.PriceResponseModel;

import java.time.LocalDateTime;

public interface IGetPriceUseCaseIn {

    PriceResponseModel getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId);

}

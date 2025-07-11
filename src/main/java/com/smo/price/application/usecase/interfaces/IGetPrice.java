package com.smo.price.application.usecase.interfaces;

import com.smo.price.application.response.price.PriceResponse;

import java.time.LocalDateTime;

public interface IGetPrice {

    PriceResponse getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId);

}

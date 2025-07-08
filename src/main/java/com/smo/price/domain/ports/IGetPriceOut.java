package com.smo.price.domain.ports;

import com.smo.price.domain.models.response.PriceResponseModel;

import java.time.LocalDateTime;
import java.util.List;

public interface IGetPriceOut {

    List<PriceResponseModel> getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId);

}

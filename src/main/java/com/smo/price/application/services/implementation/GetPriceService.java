package com.smo.price.application.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.price.application.response.price.PriceResponse;
import com.smo.price.application.services.interfaces.IGetPrice;
import com.smo.price.domain.ports.in.IGetPriceUseCaseIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class GetPriceService implements IGetPrice {

    private final IGetPriceUseCaseIn iGetPriceUseCaseIn;
    private final ObjectMapper objectMapper;

    @Override
    public PriceResponse getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId) {
        return objectMapper.convertValue(iGetPriceUseCaseIn.getPrice(applicationDate, productId, brandId, flowId), PriceResponse.class);
    }

}

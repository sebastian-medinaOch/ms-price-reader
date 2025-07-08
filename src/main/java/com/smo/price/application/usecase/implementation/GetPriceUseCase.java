package com.smo.price.application.usecase.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.price.application.response.price.PriceResponse;
import com.smo.price.application.usecase.interfaces.IGetPrice;
import com.smo.price.domain.exception.PriceNotFoundException;
import com.smo.price.domain.models.response.PriceResponseModel;
import com.smo.price.domain.ports.IGetPriceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.smo.price.application.common.ApplicationConstants.UTILITY_STRING_FORMAT_MESSAGE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GetPriceUseCase implements IGetPrice {

    private final IGetPriceOut iGetPriceOut;
    private final ObjectMapper objectMapper;

    @Override
    public PriceResponse getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId) {
        List<PriceResponseModel> prices = iGetPriceOut.getPrice(applicationDate, productId, brandId, flowId);
        return objectMapper.convertValue(getHighestPriorityPrice(prices, applicationDate, productId, brandId), PriceResponse.class);
    }

    private PriceResponseModel getHighestPriorityPrice(List<PriceResponseModel> prices, LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return prices.stream()
                .max(Comparator.comparingInt(PriceResponseModel::getPriority))
                .orElseThrow(() -> buildPriceNotFoundException(applicationDate, productId, brandId));
    }

    private PriceNotFoundException buildPriceNotFoundException(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return new PriceNotFoundException(
                String.format(UTILITY_STRING_FORMAT_MESSAGE_NOT_FOUND, applicationDate, productId, brandId)
        );
    }

}

package com.smo.price.domain.usecase;

import com.smo.price.domain.exception.PriceNotFoundException;
import com.smo.price.domain.models.response.PriceResponseModel;
import com.smo.price.domain.ports.in.IGetPriceUseCaseIn;
import com.smo.price.domain.ports.out.IGetPriceOut;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.smo.price.domain.common.DomainConstants.UTILITY_STRING_FORMAT_MESSAGE_NOT_FOUND;

@RequiredArgsConstructor
public class GetPriceUseCase implements IGetPriceUseCaseIn {

    private final IGetPriceOut iGetPriceOut;

    @Override
    public PriceResponseModel getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId, String flowId) {
        List<PriceResponseModel> prices = iGetPriceOut.getPrice(applicationDate, productId, brandId, flowId);
        return getHighestPriorityPrice(prices, applicationDate, productId, brandId);
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

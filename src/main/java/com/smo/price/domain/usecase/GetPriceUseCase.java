package com.smo.price.domain.usecase;

import com.smo.price.domain.models.response.PriceResponseModel;
import com.smo.price.domain.ports.in.IGetPriceUseCaseIn;
import com.smo.price.domain.ports.out.IGetPriceOut;
import com.smo.price.infrastructure.exception.errors.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.smo.price.domain.common.DomainConstants.MESSAGE_GET_PRICE_NOT_FOUND;
import static com.smo.price.domain.common.DomainConstants.UTILITY_STRING_FORMAT_MESSAGE_NOT_FOUND;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.EXCEPTION_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Component
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

    private ApiException buildPriceNotFoundException(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return new ApiException(
                HttpStatus.NOT_FOUND,
                MESSAGE_GET_PRICE_NOT_FOUND,
                EXCEPTION_NOT_FOUND_EXCEPTION,
                String.format(UTILITY_STRING_FORMAT_MESSAGE_NOT_FOUND, applicationDate, productId, brandId)
        );
    }

}

package com.smo.price.infrastructure.controllers.response;

import com.smo.price.application.response.common.ApiDataResponse;
import com.smo.price.application.response.price.PriceResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Contains the response to the price")
public class PriceResponseDto extends ApiDataResponse<PriceResponse> {

    public PriceResponseDto(String statusCode, String message, PriceResponse data, String flowId) {
        super(statusCode, message, data, flowId);
    }

}

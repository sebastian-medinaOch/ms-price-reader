package com.smo.price.infrastructure.controllers;


import com.smo.price.application.response.common.ApiResponse;
import com.smo.price.application.response.price.PriceResponse;
import com.smo.price.application.services.interfaces.IGetPrice;
import com.smo.price.infrastructure.utility.ResponseFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.smo.price.infrastructure.commons.InfrastructureConstants.HEADER_FLOW_ID;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.MESSAGE_APPLICATION_DATE_NOT_BLANK;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.MESSAGE_BRAND_ID_NOT_BLANK;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.MESSAGE_FLOW_ID_NOT_BLANK;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.MESSAGE_PRODUCT_ID_NOT_BLANK;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.PARAM_APPLICATION_DATE;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.PARAM_BRAND_ID;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.PARAM_PRODUCT_ID;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.PATH_GET_PRICE_CONTROLLER;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.PATH_PRODUCT_CONTROLLER;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.RETRIEVE_SUCCESS_MESSAGE;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.LOG_STRING_END_FLOW;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.LOG_STRING_INIT_FLOW;


@Log4j2
@RestController
@RequestMapping(PATH_PRODUCT_CONTROLLER)
@RequiredArgsConstructor
@Validated
public class EndpointGetPrice {

    private final IGetPrice iGetPrice;

    @GetMapping(value = PATH_GET_PRICE_CONTROLLER)
    public ResponseEntity<ApiResponse<PriceResponse>> getPrice(@RequestParam(PARAM_APPLICATION_DATE) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                               @NotNull(message = MESSAGE_APPLICATION_DATE_NOT_BLANK) LocalDateTime applicationDate,
                                                               @RequestParam(PARAM_PRODUCT_ID) @NotNull(message = MESSAGE_PRODUCT_ID_NOT_BLANK) Integer productId,
                                                               @RequestParam(PARAM_BRAND_ID) @NotNull(message = MESSAGE_BRAND_ID_NOT_BLANK) Integer brandId,
                                                               @RequestHeader(value = HEADER_FLOW_ID) @NotBlank(message = MESSAGE_FLOW_ID_NOT_BLANK) String flowId) {

        log.info(LOG_STRING_INIT_FLOW,
                flowId, applicationDate, productId, brandId);

        ResponseEntity<ApiResponse<PriceResponse>> response = buildResponse(RETRIEVE_SUCCESS_MESSAGE,
                iGetPrice.getPrice(applicationDate, productId, brandId, flowId), flowId);

        log.info(LOG_STRING_END_FLOW, flowId,
                Objects.requireNonNull(response.getBody()).getData());

        return response;
    }

    private ResponseEntity<ApiResponse<PriceResponse>> buildResponse(String layerMessage, PriceResponse response, String flowId) {
        return ResponseEntity.ok()
                .body(ResponseFactory.createSuccessResponse(HttpStatus.OK,
                        layerMessage, response, flowId));
    }

}

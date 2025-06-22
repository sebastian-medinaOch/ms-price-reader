package com.smo.price.infrastructure.controllers.interfaces;

import com.smo.price.application.response.common.ApiDataResponse;
import com.smo.price.application.response.common.ApiErrorResponse;
import com.smo.price.application.response.price.PriceResponse;
import com.smo.price.infrastructure.controllers.response.PriceResponseDto;
import com.smo.price.infrastructure.swagger.examples.ApiErrorDataResponsesExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

import static com.smo.price.infrastructure.commons.InfrastructureConstants.HEADER_FLOW_ID;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.MESSAGE_APPLICATION_DATE_NOT_BLANK;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.MESSAGE_BRAND_ID_NOT_BLANK;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.MESSAGE_FLOW_ID_NOT_BLANK;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.MESSAGE_PRODUCT_ID_NOT_BLANK;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.PARAM_APPLICATION_DATE;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.PARAM_BRAND_ID;
import static com.smo.price.infrastructure.commons.InfrastructureConstants.PARAM_PRODUCT_ID;

public interface IPriceController {

    @Operation(
            summary = "Check the applicable price for a product and brand",
            description = "Obtains the price that applies to a given combination of product, brand, and date, considering the priority among available rates.",
            tags = {"Price"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Information consulted correctly.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PriceResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(ApiErrorDataResponsesExamples.ERROR_400_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(ApiErrorDataResponsesExamples.ERROR_401_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(ApiErrorDataResponsesExamples.ERROR_403_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(ApiErrorDataResponsesExamples.ERROR_404_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "408",
                    description = "Request Timeout",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(ApiErrorDataResponsesExamples.ERROR_408_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(ApiErrorDataResponsesExamples.ERROR_409_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(ApiErrorDataResponsesExamples.ERROR_500_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "504",
                    description = "Gateway Timeout",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(ApiErrorDataResponsesExamples.ERROR_504_EXAMPLE)
                    )
            )
    })
    ResponseEntity<ApiDataResponse<PriceResponse>> getPrice(@RequestParam(PARAM_APPLICATION_DATE) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                            @NotNull(message = MESSAGE_APPLICATION_DATE_NOT_BLANK) LocalDateTime applicationDate,
                                                            @RequestParam(PARAM_PRODUCT_ID) @NotNull(message = MESSAGE_PRODUCT_ID_NOT_BLANK) Integer productId,
                                                            @RequestParam(PARAM_BRAND_ID) @NotNull(message = MESSAGE_BRAND_ID_NOT_BLANK) Integer brandId,
                                                            @RequestHeader(value = HEADER_FLOW_ID) @NotBlank(message = MESSAGE_FLOW_ID_NOT_BLANK) String flowId);

}

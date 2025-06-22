package com.smo.price.application.response.price;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response containing applicable price information for a product in a specific brand within a date range.")
public class PriceResponse {

    @Schema(description = "Product identifier", example = "35455")
    private Integer productId;
    @Schema(description = "Brand identifier", example = "1")
    private Integer brandId;
    @Schema(description = "Identifier of the applied price rate", example = "2")
    private Integer priceList;
    @Schema(description = "Price application start date", example = "2020-06-14T00:00:00")
    private LocalDateTime startDate;
    @Schema(description = "Price application end date", example = "2020-12-31T23:59:59")
    private LocalDateTime endDate;
    @Schema(description = "Final price applicable to the product", example = "35.50")
    private BigDecimal price;

}
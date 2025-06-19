package com.smo.price.domain.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DomainConstants {

    /* Constantes Extras de utilidad */
    public static final String UTILITY_STRING_FORMAT_MESSAGE_NOT_FOUND = "No information was found with this data: applicationDate = %s, productId = %d, brandId = %d";

    //MessageError
    public static final String MESSAGE_GET_PRICE_NOT_FOUND = "No prices were found for the given parameters: application date, product ID, and brand ID.";

}

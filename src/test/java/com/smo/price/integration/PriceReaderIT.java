package com.smo.price.integration;

import com.intuit.karate.junit5.Karate;

public class PriceReaderIT {

    @Karate.Test
    Karate testPriceReader() {
        return Karate.run("classpath:features/price-reader.feature");
    }

}

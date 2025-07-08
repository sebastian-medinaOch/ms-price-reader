package com.smo.price.infrastructure.configuration;

import com.smo.price.domain.ports.in.IGetPriceUseCaseIn;
import com.smo.price.domain.ports.out.IGetPriceOut;
import com.smo.price.domain.usecase.GetPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public IGetPriceUseCaseIn getPriceUseCase(IGetPriceOut iGetPriceOut) {
        return new GetPriceUseCase(iGetPriceOut);
    }

}

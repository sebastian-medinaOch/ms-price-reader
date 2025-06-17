package com.smo.price.infrastructure.commons.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_MESSAGE_ID_EMPTY;
import static com.smo.price.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_PRODUCT_ID_EMPTY;

@ExtendWith(MockitoExtension.class)
class UtilityTest {

    @InjectMocks
    private Utility utility;

    @Test
    void validateDataSuccess() {
        String validProductId = "123";
        String validMessageId = "msg-456";

        Mono<Void> result = utility.validateData(validProductId, validMessageId);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void validateDataProductIdIsEmpty() {
        String emptyProductId = "";
        String validMessageId = "msg-456";

        Mono<Void> result = utility.validateData(emptyProductId, validMessageId);

        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assert error instanceof BussinessException;
                    BussinessException exception = (BussinessException) error;
                    assert exception.getStatus() == HttpStatus.BAD_REQUEST;
                    assert exception.getDetail().equals(MESSAGE_ERROR_PRODUCT_ID_EMPTY);
                })
                .verify();
    }

    @Test
    void validateDataMessageIdIsEmpty() {
        String emptyProductId = "123";
        String validMessageId = "";

        Mono<Void> result = utility.validateData(emptyProductId, validMessageId);

        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assert error instanceof BussinessException;
                    BussinessException exception = (BussinessException) error;
                    assert exception.getStatus() == HttpStatus.BAD_REQUEST;
                    assert exception.getDetail().equals(MESSAGE_ERROR_MESSAGE_ID_EMPTY);
                })
                .verify();
    }

    @Test
    void validateDataProductIdIsNull() {
        String emptyProductId = null;
        String validMessageId = "msg-456";

        Mono<Void> result = utility.validateData(emptyProductId, validMessageId);

        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assert error instanceof BussinessException;
                    BussinessException exception = (BussinessException) error;
                    assert exception.getStatus() == HttpStatus.BAD_REQUEST;
                    assert exception.getDetail().equals(MESSAGE_ERROR_PRODUCT_ID_EMPTY);
                })
                .verify();
    }

    @Test
    void validateDataMessageIdIsNull() {
        String emptyProductId = "123";
        String validMessageId = null;

        Mono<Void> result = utility.validateData(emptyProductId, validMessageId);

        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assert error instanceof BussinessException;
                    BussinessException exception = (BussinessException) error;
                    assert exception.getStatus() == HttpStatus.BAD_REQUEST;
                    assert exception.getDetail().equals(MESSAGE_ERROR_MESSAGE_ID_EMPTY);
                })
                .verify();
    }

}
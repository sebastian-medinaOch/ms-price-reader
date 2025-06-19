package com.smo.price.infrastructure.utility;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.smo.price.infrastructure.exception.errors.ApiException;
import jakarta.validation.ConstraintViolationException;
import lombok.experimental.UtilityClass;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.smo.price.infrastructure.commons.ExceptionsConstants.EXCEPTION_RESOURCE_FOUND_EXCEPTION;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.UTILITY_EXCEPTION_MESSAGE;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.UTILITY_EXCEPTION_TYPE;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.UTILITY_KEY_NAME_EXCEPTION_MESSAGE;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.UTILITY_KEY_NAME_EXCEPTION_TYPE;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.UTILITY_KEY_VALUE_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE;
import static com.smo.price.infrastructure.commons.ExceptionsConstants.UTILITY_STRING_FORMAT_INCORRECT;


@UtilityClass
public class ExceptionFieldMapper {

    public static Map<String, String> fromApiException(ApiException exception) {
        return Map.of(
                UTILITY_EXCEPTION_TYPE, exception.getClass().getSimpleName(),
                UTILITY_EXCEPTION_MESSAGE, exception.getAdditionalData().toString()
        );
    }

    public static Map<String, String> fromGenericException(Exception exception) {
        return Map.of(
                UTILITY_EXCEPTION_TYPE, exception.getClass().getSimpleName(),
                UTILITY_EXCEPTION_MESSAGE, exception.getMessage()
        );
    }


    public static Map<String, String> fromValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> fields = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            fields.put(UTILITY_KEY_NAME_EXCEPTION_TYPE, error.getClass().getSimpleName());
            fields.put(UTILITY_KEY_NAME_EXCEPTION_MESSAGE, error.getDefaultMessage());
        });
        return fields;
    }

    public static Map<String, String> fromMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        return Map.of(
                UTILITY_EXCEPTION_TYPE, exception.getClass().getSimpleName(),
                UTILITY_EXCEPTION_MESSAGE, String.format(UTILITY_STRING_FORMAT_INCORRECT,
                        exception.getName(), exception.getValue())
        );
    }

    public static Map<String, String> fromConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> fields = new HashMap<>();
        exception.getConstraintViolations().forEach(violation -> {
            fields.put(UTILITY_EXCEPTION_TYPE, violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());
            fields.put(UTILITY_EXCEPTION_MESSAGE, violation.getMessage());
        });
        return fields;
    }

    public static Map<String, String> fromMissingRequestParameter(MissingServletRequestParameterException exception) {
        return Map.of(
                UTILITY_EXCEPTION_TYPE, exception.getClass().getSimpleName(),
                UTILITY_EXCEPTION_MESSAGE, exception.getMessage()
        );
    }

    public static Map<String, String> fromIllegalArgumentException(IllegalArgumentException exception) {
        String message = (exception.getCause() instanceof InvalidFormatException invalidFormatException)
                ? UTILITY_KEY_VALUE_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE + invalidFormatException.getPath().getFirst().getFieldName()
                : exception.getMessage();
        return Map.of(
                UTILITY_KEY_NAME_EXCEPTION_TYPE, exception.getClass().getSimpleName(),
                UTILITY_KEY_NAME_EXCEPTION_MESSAGE, message
        );
    }

    public static Map<String, String> fromMissingHeaderException(MissingRequestHeaderException exception) {
        return Map.of(
                UTILITY_KEY_NAME_EXCEPTION_TYPE, exception.getClass().getSimpleName(),
                UTILITY_KEY_NAME_EXCEPTION_MESSAGE, Objects.requireNonNull(exception.getBody().getDetail())
        );
    }

    public static Map<String, String> fromNoResourceFoundException(NoResourceFoundException exception) {
        return Map.of(
                UTILITY_KEY_NAME_EXCEPTION_TYPE, exception.getClass().getSimpleName(),
                UTILITY_KEY_NAME_EXCEPTION_MESSAGE, EXCEPTION_RESOURCE_FOUND_EXCEPTION
        );
    }

}

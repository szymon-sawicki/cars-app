package com.app.domain.config.validator;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Parent interface used to validation of objects.
 * @param <T> type  of validated object
 * @author Szymon Sawicki
 */


public interface Validator<T> {

    /**
     * Implementations of that method are used to exact validation of objects
     * @param t object to validate
     * @return map with errors (key - place of error, value - error message)
     */

    Map<String,String> validate(T t);

    /**
     * static method that provide validation.
     * If errors are found, exception will be thrown with message containing all errors concatenated in one string
     * @param validator class that implements validate method
     * @param t object to validate
     * @param <T> type of object to validate
     */

    static <T> void validate(Validator<T> validator, T t) {
        var errors = validator.validate(t);
        if(!errors.isEmpty()) {
            var message = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));
            throw new ValidatorException("[VALIDATION ERRORS]: " + message);
        }

    }


}

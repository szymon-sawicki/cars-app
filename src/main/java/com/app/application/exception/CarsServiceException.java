package com.app.application.exception;

public class CarsServiceException extends RuntimeException {
    public CarsServiceException (String message) {
        super(message);
    }
}

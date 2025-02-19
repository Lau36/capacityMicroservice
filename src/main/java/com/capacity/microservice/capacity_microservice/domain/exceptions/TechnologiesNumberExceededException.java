package com.capacity.microservice.capacity_microservice.domain.exceptions;

public class TechnologiesNumberExceededException extends RuntimeException {
    public TechnologiesNumberExceededException(String message) {
        super(message);
    }
}

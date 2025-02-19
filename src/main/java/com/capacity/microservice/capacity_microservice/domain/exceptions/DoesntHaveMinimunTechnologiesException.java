package com.capacity.microservice.capacity_microservice.domain.exceptions;

public class DoesntHaveMinimunTechnologiesException extends RuntimeException {
    public DoesntHaveMinimunTechnologiesException(String message) {
        super(message);
    }
}

package com.capacity.microservice.capacity_microservice.domain.exceptions;

public class AlreadyHaveSameTechnologyAsociatedException extends RuntimeException {
    public AlreadyHaveSameTechnologyAsociatedException(String message) {
        super(message);
    }
}

package com.capacity.microservice.capacity_microservice.infrastructure.exceptionHandler;

import com.capacity.microservice.capacity_microservice.domain.exceptions.AlreadyHaveSameTechnologyAsociatedException;
import com.capacity.microservice.capacity_microservice.domain.exceptions.DoesntHaveMinimunTechnologiesException;
import com.capacity.microservice.capacity_microservice.domain.exceptions.TechnologiesNotFoud;
import com.capacity.microservice.capacity_microservice.domain.exceptions.TechnologiesNumberExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AlreadyHaveSameTechnologyAsociatedException.class)
    public Mono<ResponseEntity<String>> handleAlreadyHaveSameTechnologyAsociated(AlreadyHaveSameTechnologyAsociatedException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(DoesntHaveMinimunTechnologiesException.class)
    public Mono<ResponseEntity<String>> handleDoesntHaveMinimunTechnologies(DoesntHaveMinimunTechnologiesException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(TechnologiesNotFoud.class)
    public Mono<ResponseEntity<String>> handleTechnologiesNotFoud(TechnologiesNotFoud ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(TechnologiesNumberExceededException.class)
    public Mono<ResponseEntity<String>> handleTechnologiesNumberExceeded(TechnologiesNumberExceededException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

}

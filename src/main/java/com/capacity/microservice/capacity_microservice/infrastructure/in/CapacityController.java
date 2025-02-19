package com.capacity.microservice.capacity_microservice.infrastructure.in;

import com.capacity.microservice.capacity_microservice.application.dto.request.CapacityRequest;
import com.capacity.microservice.capacity_microservice.application.handler.ICapacityRestHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.capacity.microservice.capacity_microservice.infrastructure.utils.constants.InfraConstans.CAPACITY_PATH;

@RestController()
@AllArgsConstructor
@RequestMapping(CAPACITY_PATH)
public class CapacityController {

    private final ICapacityRestHandler capacityRestHandler;

    @PostMapping
    public Mono<ResponseEntity<Void>> technology(@RequestBody CapacityRequest request) {
        return capacityRestHandler.createCapacity(request).then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }
}

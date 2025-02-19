package com.capacity.microservice.capacity_microservice.domain.ports.in;

import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import reactor.core.publisher.Mono;

public interface ICapacityServicePort {
    Mono<Void> createCapacity(CapacityModel capacityModel);
}

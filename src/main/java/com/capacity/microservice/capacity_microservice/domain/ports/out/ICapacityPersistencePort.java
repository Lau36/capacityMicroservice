package com.capacity.microservice.capacity_microservice.domain.ports.out;

import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import reactor.core.publisher.Mono;

public interface ICapacityPersistencePort {
    Mono<CapacityModel> saveCapacity(CapacityModel capacityModel);
    Mono<Void> deleteCapacity(Long capacityId);

}

package com.capacity.microservice.capacity_microservice.domain.ports.in;

import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesAndTechnologiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.Pagination;
import reactor.core.publisher.Mono;

public interface ICapacityServicePort {
    Mono<Void> createCapacity(CapacityModel capacityModel);
    Mono<CapacitiesAndTechnologiesPaginated> getAllCapacities(Pagination pagination);
}

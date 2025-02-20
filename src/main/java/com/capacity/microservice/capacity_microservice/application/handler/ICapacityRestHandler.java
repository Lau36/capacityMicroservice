package com.capacity.microservice.capacity_microservice.application.handler;

import com.capacity.microservice.capacity_microservice.application.dto.request.CapacityRequest;
import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesAndTechnologiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.Pagination;
import reactor.core.publisher.Mono;

public interface ICapacityRestHandler {
    Mono<Void> createCapacity(CapacityRequest capacityRequest);
    Mono<CapacitiesAndTechnologiesPaginated> getAllCapacities(Pagination pagination);
}

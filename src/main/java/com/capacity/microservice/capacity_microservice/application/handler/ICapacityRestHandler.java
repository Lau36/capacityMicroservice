package com.capacity.microservice.capacity_microservice.application.handler;

import com.capacity.microservice.capacity_microservice.application.dto.request.CapacityRequest;
import com.capacity.microservice.capacity_microservice.application.dto.response.CapacityResponse;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesAndTechnologiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.Pagination;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapacityRestHandler {
    Mono<Void> createCapacity(CapacityRequest capacityRequest);
    Mono<CapacitiesAndTechnologiesPaginated> getAllCapacities(Pagination pagination);
    Flux<CapacityResponse> getCapacities(List<Long> capacitiesIds);
    Mono<Boolean> existCapacitiesById(List<Long> capacitiesId);
}

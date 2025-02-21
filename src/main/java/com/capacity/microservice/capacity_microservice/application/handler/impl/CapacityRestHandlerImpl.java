package com.capacity.microservice.capacity_microservice.application.handler.impl;

import com.capacity.microservice.capacity_microservice.application.dto.request.CapacityRequest;
import com.capacity.microservice.capacity_microservice.application.dto.response.CapacityResponse;
import com.capacity.microservice.capacity_microservice.application.handler.ICapacityRestHandler;
import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.ports.in.ICapacityServicePort;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesAndTechnologiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.Pagination;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class CapacityRestHandlerImpl implements ICapacityRestHandler {
    private final ICapacityServicePort capacityServicePort;

    @Override
    public Mono<Void> createCapacity(CapacityRequest capacityRequest) {
        CapacityModel model = new CapacityModel(capacityRequest.getId(),
                capacityRequest.getName(),
                capacityRequest.getDescription(),
                capacityRequest.getTechnologiesIds(),
                null);
        return capacityServicePort.createCapacity(model);
    }

    @Override
    public Mono<CapacitiesAndTechnologiesPaginated> getAllCapacities(Pagination pagination) {
        return capacityServicePort.getAllCapacities(pagination);
    }

    @Override
    public Flux<CapacityResponse> getCapacities(List<Long> capacitiesIds) {
        return capacityServicePort.getCapacities(capacitiesIds).map(
                capacity -> new CapacityResponse(
                        capacity.getId(),
                        capacity.getName(),
                        capacity.getTechnologies()
                )
        );
    }

    @Override
    public Mono<Boolean> existCapacitiesById(List<Long> capacitiesId) {
        return capacityServicePort.existCapacitiesById(capacitiesId);
    }
}

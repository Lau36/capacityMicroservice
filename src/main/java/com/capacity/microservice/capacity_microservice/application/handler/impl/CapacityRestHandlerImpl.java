package com.capacity.microservice.capacity_microservice.application.handler.impl;

import com.capacity.microservice.capacity_microservice.application.dto.request.CapacityRequest;
import com.capacity.microservice.capacity_microservice.application.handler.ICapacityRestHandler;
import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.ports.in.ICapacityServicePort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CapacityRestHandlerImpl implements ICapacityRestHandler {
    private final ICapacityServicePort capacityServicePort;

    @Override
    public Mono<Void> createCapacity(CapacityRequest capacityRequest) {
        CapacityModel model = new CapacityModel(capacityRequest.getId(), capacityRequest.getName(), capacityRequest.getDescription(), capacityRequest.getTechnologiesIds());
        return capacityServicePort.createCapacity(model);
    }
}

package com.capacity.microservice.capacity_microservice.infrastructure.out.adapter;

import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ICapacityPersistencePort;
import com.capacity.microservice.capacity_microservice.infrastructure.out.entity.CapacityEntity;
import com.capacity.microservice.capacity_microservice.infrastructure.out.repository.ICapacityRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {

    private ICapacityRepository capacityRepository;

    @Override
    public Mono<CapacityModel> saveCapacity(CapacityModel capacityModel) {
        CapacityEntity entity = new CapacityEntity(capacityModel.getId(), capacityModel.getName(), capacityModel.getDescription());
        return capacityRepository.save(entity)
                .map(savedEntity ->
                        new CapacityModel(
                                savedEntity.getId(),
                                savedEntity.getName(),
                                savedEntity.getDescription(),
                                capacityModel.getTechnologiesIds()
                        )
                );
    }

    @Override
    public Mono<Void> deleteCapacity(Long capacityId) {
        return capacityRepository.deleteById(capacityId);
    }
}

package com.capacity.microservice.capacity_microservice.infrastructure.out.repository;

import com.capacity.microservice.capacity_microservice.infrastructure.out.entity.CapacityEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapacityRepository extends R2dbcRepository<CapacityEntity, Long> {
    Mono<Void> deleteById(Long capacityId);
    Flux<CapacityEntity> findAllBy(PageRequest pageable);
    Mono<CapacityEntity> findById(Long capacityId);
    Flux<CapacityEntity> findAllByIdIn(List<Long> capacityIds);
    Mono<Long> countByIdIn(List<Long> capacityIds);

}

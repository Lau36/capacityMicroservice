package com.capacity.microservice.capacity_microservice.infrastructure.out.repository;

import com.capacity.microservice.capacity_microservice.infrastructure.out.entity.CapacityEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICapacityRepository extends R2dbcRepository<CapacityEntity, Long> {
    Mono<Void> deleteById(Long capacityId);
    Flux<CapacityEntity> findAllBy(PageRequest pageable);

}

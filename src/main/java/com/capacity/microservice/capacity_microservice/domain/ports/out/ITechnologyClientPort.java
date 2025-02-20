package com.capacity.microservice.capacity_microservice.domain.ports.out;

import com.capacity.microservice.capacity_microservice.domain.utils.Technologies;
import com.capacity.microservice.capacity_microservice.domain.utils.TechnologyIdsDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyClientPort {
    Mono<Boolean> existTechnologies(TechnologyIdsDTO technologyIds);
    Mono<Void> associateTechnologies(List<Long> technologyIds, Long capacityId);
    Flux<Technologies> technologiesAssociateToCapacityId(Integer capacityId);
}

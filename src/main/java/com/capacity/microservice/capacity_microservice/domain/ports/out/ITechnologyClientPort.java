package com.capacity.microservice.capacity_microservice.domain.ports.out;

import com.capacity.microservice.capacity_microservice.domain.utils.TechnologyIdName;
import com.capacity.microservice.capacity_microservice.domain.utils.TechnologyIds;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyClientPort {
    Mono<Boolean> existTechnologies(TechnologyIds technologyIds);
    Mono<Void> associateTechnologies(List<Long> technologyIds, Long capacityId);
    Flux<TechnologyIdName> technologiesAssociateToCapacityId(Integer capacityId);
}

package com.capacity.microservice.capacity_microservice.domain.ports.out;

import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.Pagination;
import reactor.core.publisher.Mono;

public interface ICapacityPersistencePort {
    Mono<CapacityModel> saveCapacity(CapacityModel capacityModel);
    Mono<Void> deleteCapacity(Long capacityId);
    Mono<CapacitiesPaginated> listAllCapacities(Pagination pagination);

}

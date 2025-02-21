package com.capacity.microservice.capacity_microservice.infrastructure.out.adapter;

import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ICapacityPersistencePort;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.Pagination;
import com.capacity.microservice.capacity_microservice.infrastructure.out.entity.CapacityEntity;
import com.capacity.microservice.capacity_microservice.infrastructure.out.repository.ICapacityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {

    private ICapacityRepository capacityRepository;

    @Override
    public Mono<CapacityModel> saveCapacity(CapacityModel capacityModel) {
        Integer technologiesCount = capacityModel.getTechnologiesIds().size();
        CapacityEntity entity = new CapacityEntity(capacityModel.getId(), capacityModel.getName(), capacityModel.getDescription(), technologiesCount);


        return capacityRepository.save(entity)
                .map(savedEntity ->
                        new CapacityModel(
                                savedEntity.getId(),
                                savedEntity.getName(),
                                savedEntity.getDescription(),
                                capacityModel.getTechnologiesIds(),
                                savedEntity.getTechnology_count()
                        )
                );
    }

    @Override
    public Mono<Void> deleteCapacity(Long capacityId) {
        return capacityRepository.deleteById(capacityId);
    }

    @Override
    public Mono<CapacitiesPaginated> listAllCapacities(Pagination pagination) {
        Sort sort = Sort.by(Sort.Direction.fromString(pagination.getSortDirection().name()), pagination.getSort());
        PageRequest pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), sort);
        Mono<List<CapacityModel>> capacities =
                capacityRepository.findAllBy(pageable)
                        .map(this::toModel)
                        .collectList();

        Mono<Long> totalElements = capacityRepository.count()
                .defaultIfEmpty(0L);

        return Mono.zip(capacities, totalElements).map(
                tuple -> {
                    long totalElementsValue = tuple.getT2();
                    int totalPages = (int) Math.ceil((double) totalElementsValue / pagination.getSize());

                    return new CapacitiesPaginated(
                            tuple.getT1(),
                            pagination.getPage(),
                            totalElementsValue,
                            totalPages
                    );
                });
    }

    @Override
    public Flux<CapacityModel> getCapacities(List<Long> capacitiesId) {
        return capacityRepository.findAllByIdIn(capacitiesId).map(this::toModel);
    }

    @Override
    public Mono<Boolean> existCapacitiesById(List<Long> capacitiesId) {
        return capacityRepository.countByIdIn(capacitiesId).map(
                count -> count == capacitiesId.size()
        );
    }

    public CapacityModel toModel(CapacityEntity capacityEntity) {
        return new CapacityModel(capacityEntity.getId(), capacityEntity.getName(), capacityEntity.getDescription(), null, null);
    }
}

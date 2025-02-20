package com.capacity.microservice.capacity_microservice.domain.useCase;

import com.capacity.microservice.capacity_microservice.domain.exceptions.AlreadyHaveSameTechnologyAsociatedException;
import com.capacity.microservice.capacity_microservice.domain.exceptions.DoesntHaveMinimunTechnologiesException;
import com.capacity.microservice.capacity_microservice.domain.exceptions.TechnologiesNotFoud;
import com.capacity.microservice.capacity_microservice.domain.exceptions.TechnologiesNumberExceededException;
import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.ports.in.ICapacityServicePort;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ICapacityPersistencePort;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ITechnologyClientPort;
import com.capacity.microservice.capacity_microservice.domain.utils.Capacities;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesAndTechnologiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.Pagination;
import com.capacity.microservice.capacity_microservice.domain.utils.TechnologyIdsDTO;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.capacity.microservice.capacity_microservice.domain.utils.constans.DomainConstans.*;

@AllArgsConstructor
public class CapacityUseCase implements ICapacityServicePort {
    private final ICapacityPersistencePort capacityPersistencePort;
    private final ITechnologyClientPort technologyClientPort;

    @Override
    public Mono<Void> createCapacity(CapacityModel capacityModel) {
        TechnologyIdsDTO dto = new TechnologyIdsDTO((capacityModel.getTechnologiesIds()));
        return validationsCapacity(capacityModel)
                .then(technologyClientPort.existTechnologies(dto))
                .flatMap(exist -> {
                    if (!exist) {
                        return Mono.error(new TechnologiesNotFoud(TECHNOLOGIES_DOESNT_EXISTS));
                    }

                    List<Long> technologiesId = capacityModel.getTechnologiesIds().stream().map(Long::parseLong).toList();

                    return capacityPersistencePort.saveCapacity(capacityModel)
                            .flatMap(savedCapacity ->
                                    technologyClientPort.associateTechnologies(technologiesId, savedCapacity.getId())
                                            .thenReturn(savedCapacity)
                                            .onErrorResume(
                                                    error -> capacityPersistencePort.deleteCapacity(savedCapacity.getId())
                                                    .then(Mono.error(error)))
                            ).then();
                });
    }

    @Override
    public Mono<CapacitiesAndTechnologiesPaginated> getAllCapacities(Pagination pagination) {
        return capacityPersistencePort.listAllCapacities(pagination)
                .flatMap(capacitiesPaginated -> {

                    if (capacitiesPaginated == null) {
                        return Mono.error(new IllegalStateException("capacitiesPaginated es nulo"));
                    }

                    long totalElements = capacitiesPaginated.getTotalElements();
                    int totalPages = capacitiesPaginated.getTotalPages();

                    return Flux.fromIterable(capacitiesPaginated.getCapacities())
                            .flatMap(capacityModel ->
                                    technologyClientPort.technologiesAssociateToCapacityId(capacityModel.getId().intValue())
                                            .collectList()
                                            .map(technologies -> new Capacities(
                                                    capacityModel.getId(),
                                                    capacityModel.getName(),
                                                    capacityModel.getDescription(),
                                                    technologies))
                            )
                            .collectList()
                            .map(capacitiesList -> new CapacitiesAndTechnologiesPaginated(
                                    capacitiesList,
                                    pagination.getPage(),
                                    totalPages,
                                    totalElements
                            ));
                });
    }

    public Mono<Void> validationsCapacity(CapacityModel capacityModel) {
        if (capacityModel.getTechnologiesIds().size() < MINIMUM_TECHNOLOGIES_ASOCIATE) {
            return Mono.error(new DoesntHaveMinimunTechnologiesException(String.format(DOESNT_HAVE_MINIMUN_TECHNOLOGIES, MINIMUM_TECHNOLOGIES_ASOCIATE)));
        }
        if (capacityModel.getTechnologiesIds().size() > MAXIMUN_TECHNOLOGIES_ASOCIATE) {
            return Mono.error(new TechnologiesNumberExceededException(String.format(TECHNOLOGIES_NUMBER_EXCEEDED, MAXIMUN_TECHNOLOGIES_ASOCIATE)));
        }


        Set<String> uniqueTechnologies = new HashSet<>(capacityModel.getTechnologiesIds());

        if (uniqueTechnologies.size() != capacityModel.getTechnologiesIds().size()) {
            return Mono.error(new AlreadyHaveSameTechnologyAsociatedException(CANNOT_REPEAT_TECHNOLOGIES));
        }

        return Mono.empty();
    }

}

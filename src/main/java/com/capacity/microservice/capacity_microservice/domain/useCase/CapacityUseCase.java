package com.capacity.microservice.capacity_microservice.domain.useCase;

import com.capacity.microservice.capacity_microservice.domain.exceptions.DoesntHaveMinimunTechnologiesException;
import com.capacity.microservice.capacity_microservice.domain.exceptions.TechnologiesNumberExceededException;
import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.ports.in.ICapacityServicePort;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ICapacityPersistencePort;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ITechnologyClientPort;
import com.capacity.microservice.capacity_microservice.domain.utils.constans.TechnologyCapacityDTO;
import com.capacity.microservice.capacity_microservice.domain.utils.constans.TechnologyIdsDTO;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                        return Mono.error(new IllegalArgumentException("Algunas tecnologías no existen."));
                    }

                    List<Long> technologiesId = capacityModel.getTechnologiesIds().stream().map(Long::parseLong).toList();

                    return capacityPersistencePort.saveCapacity(capacityModel)
                            .flatMap(savedCapacity ->
                                    technologyClientPort.associateTechnologies(technologiesId, savedCapacity.getId())
                                            .thenReturn(savedCapacity)
                                            .onErrorResume(error -> capacityPersistencePort.deleteCapacity(savedCapacity.getId())
                                                    .then(Mono.error(error)))
                            ).then();
                });
    }

    public Mono<Void> validationsCapacity(CapacityModel capacityModel) {
        if (capacityModel.getTechnologiesIds().size() < 3) {
            return Mono.error(new DoesntHaveMinimunTechnologiesException("La capacidad debe tener al menos 3 tecnologías."));
        }
        if (capacityModel.getTechnologiesIds().size() > 20) {
            return Mono.error(new TechnologiesNumberExceededException("La capacidad no puede tener más de 20 tecnologías."));
        }


        Set<String> uniqueTechnologies = new HashSet<>(capacityModel.getTechnologiesIds());

        if (uniqueTechnologies.size() != capacityModel.getTechnologiesIds().size()) {
            return Mono.error(new IllegalArgumentException("No se pueden repetir tecnologías en una capacidad."));
        }

        return Mono.empty();
    }

}

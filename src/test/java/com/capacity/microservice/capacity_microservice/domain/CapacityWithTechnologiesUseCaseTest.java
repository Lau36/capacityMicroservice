package com.capacity.microservice.capacity_microservice.domain;

import com.capacity.microservice.capacity_microservice.domain.exceptions.AlreadyHaveSameTechnologyAsociatedException;
import com.capacity.microservice.capacity_microservice.domain.exceptions.DoesntHaveMinimunTechnologiesException;
import com.capacity.microservice.capacity_microservice.domain.exceptions.TechnologiesNotFoud;
import com.capacity.microservice.capacity_microservice.domain.exceptions.TechnologiesNumberExceededException;
import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ICapacityPersistencePort;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ITechnologyClientPort;
import com.capacity.microservice.capacity_microservice.domain.useCase.CapacityUseCase;
import com.capacity.microservice.capacity_microservice.domain.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CapacityWithTechnologiesUseCaseTest {

    @Mock
    private ICapacityPersistencePort capacityPersistencePort;

    @Mock
    private ITechnologyClientPort technologyClientPort;

    @InjectMocks
    private CapacityUseCase capacityUseCase;

    private static CapacityModel capacityModel;


    @BeforeEach
    void setUp(){
       capacityModel = new CapacityModel(
                1L,
                "Test Capacity",
                "Desc",
                List.of("1", "2", "3"),
                3);
    }
    @Test
    void createCapacity_Success() {
        List<Long> technologiesId = capacityModel.getTechnologiesIds().stream().map(Long::parseLong).toList();
        CapacityModel savedCapacity = new CapacityModel(1L, "Test Capacity", "Desc", List.of("1", "2", "3"), 3);

        Mockito.when(technologyClientPort.existTechnologies(ArgumentMatchers.any())).thenReturn(Mono.just(true));
        Mockito.when(capacityPersistencePort.saveCapacity(capacityModel)).thenReturn(Mono.just(savedCapacity));
        Mockito.when(technologyClientPort.associateTechnologies(technologiesId, savedCapacity.getId())).thenReturn(Mono.empty());

        Mono<Void> result = capacityUseCase.createCapacity(capacityModel);

        StepVerifier.create(result).verifyComplete();

        verify(capacityPersistencePort, times(1)).saveCapacity(capacityModel);
        verify(technologyClientPort, times(1)).associateTechnologies(technologiesId, savedCapacity.getId());
    }

    @Test
    void createCapacity_FailsAndDeletesSavedCapacity() {
        CapacityModel capacityModel = new CapacityModel(1L, "Test Capacity", "Desc", List.of("1", "2", "3"), 3);
        List<Long> technologiesId = capacityModel.getTechnologiesIds().stream().map(Long::parseLong).toList();
        CapacityModel savedCapacity = new CapacityModel(1L, "Test Capacity", "Desc", List.of("1", "2", "3"), 3);

        Mockito.when(technologyClientPort.existTechnologies(ArgumentMatchers.any(TechnologyIds.class)))
                .thenReturn(Mono.just(true));

        Mockito.when(capacityPersistencePort.saveCapacity(capacityModel))
                .thenReturn(Mono.just(savedCapacity));

        Mockito.when(technologyClientPort.associateTechnologies(technologiesId, savedCapacity.getId()))
                .thenReturn(Mono.error(new RuntimeException("Error al asociar tecnologías")));

        Mockito.when(capacityPersistencePort.deleteCapacity(savedCapacity.getId()))
                .thenReturn(Mono.empty());

        Mono<Void> result = capacityUseCase.createCapacity(capacityModel);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(capacityPersistencePort, times(1)).saveCapacity(capacityModel);
        verify(technologyClientPort, times(1)).associateTechnologies(technologiesId, savedCapacity.getId());
        verify(capacityPersistencePort, times(1)).deleteCapacity(savedCapacity.getId());
    }

    @Test
    void createCapacity_FailsWhenTechnologiesDoNotExist() {

        Mockito.when(technologyClientPort.existTechnologies(ArgumentMatchers.any())).thenReturn(Mono.just(false));

        Mono<Void> result = capacityUseCase.createCapacity(capacityModel);

        StepVerifier.create(result)
                .expectError(TechnologiesNotFoud.class)
                .verify();
    }

    @Test
    void getAllCapacities_Success() {
        Pagination pagination = new Pagination(1, 10, "name", SortDirection.ASC);

        CapacitiesPaginated capacitiesPaginated = new CapacitiesPaginated(List.of(capacityModel)
                , 1, 2L, 5);

        TechnologyIdName technologyIdName = new TechnologyIdName(1L, "Tech 1");

        Mockito.when(capacityPersistencePort.listAllCapacities(pagination)).thenReturn(Mono.just(capacitiesPaginated));
        Mockito.when(technologyClientPort.technologiesAssociateToCapacityId(anyInt())).thenReturn(Flux.just(technologyIdName));

        Mono<CapacitiesAndTechnologiesPaginated> result = capacityUseCase.getAllCapacities(pagination);

        StepVerifier.create(result)
                .expectNextMatches(
                        response ->
                                response.getTotalElements() == 2L && response.getTotalPages() == 5)
                .verifyComplete();
    }

    @Test
    void getAllCapacitiesTest() {
        CapacityModel capacityModel = new CapacityModel(1L, "Test Capacity", "Desc", List.of("1", "2", "3"), 3);
        TechnologyIdName technologyIdName = new TechnologyIdName(1L, "Tech 1");
        CapacityWithTechnologies capacityWithTechnologies = new CapacityWithTechnologies(1L, "Test Capacity", "Desc", List.of(technologyIdName));

        Mockito.when(capacityPersistencePort.getCapacities(ArgumentMatchers.any())).thenReturn(Flux.just(capacityModel));
        Mockito.when(technologyClientPort.technologiesAssociateToCapacityId(ArgumentMatchers.any())).thenReturn(Flux.just(technologyIdName));
        Flux<CapacityWithTechnologies> result = capacityUseCase.getCapacities(ArgumentMatchers.any());

        StepVerifier.create(result)
                .assertNext(actualCapacities -> {
                    assertEquals(capacityWithTechnologies.getId(), actualCapacities.getId());
                    assertEquals(capacityWithTechnologies.getName(), actualCapacities.getName());
                    assertEquals(capacityWithTechnologies.getDescription(), actualCapacities.getDescription());
                    assertEquals(capacityWithTechnologies.getTechnologies(), actualCapacities.getTechnologies());
                })
                .verifyComplete();

        verify(capacityPersistencePort, times(1)).getCapacities(ArgumentMatchers.any());

    }

    @Test
    void existsCapacititesTest(){
        List<Long> capacitiesId = List.of(1L, 2L, 3L);

        Mockito.when(capacityPersistencePort.existCapacitiesById(capacitiesId)).thenReturn(Mono.just(true));

        Mono<Boolean> result = capacityUseCase.existCapacitiesById(capacitiesId);

        StepVerifier.create(result).expectNext(true).verifyComplete();
    }

    @Test
    void validationsCapacity_Success() {

        StepVerifier.create(capacityUseCase.validationsCapacity(capacityModel)).verifyComplete();
    }

    @Test
    void validationsCapacity_FailsWhenTooFewTechnologies() {
        CapacityModel capacityModel = new CapacityModel(1L, "Test Capacity", "Desc", List.of("1"), 1);
        StepVerifier.create(capacityUseCase.validationsCapacity(capacityModel))
                .expectError(DoesntHaveMinimunTechnologiesException.class)
                .verify();
    }

    @Test
    void validationsCapacity_FailsWhenTooManyTechnologies() {
        List<String> technologies = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21");

        CapacityModel capacityModel = new CapacityModel(1L, "Test Capacity", "Desc", technologies, 21);

        StepVerifier.create(capacityUseCase.validationsCapacity(capacityModel))
                .expectError(TechnologiesNumberExceededException.class)
                .verify();
    }

    @Test
    void validationsCapacity_FailsWhenDuplicateTechnologies() {
        CapacityModel capacityModel = new CapacityModel(1L, "Test Capacity", "Desc", Arrays.asList("1", "2", "2"), 3);
        StepVerifier.create(capacityUseCase.validationsCapacity(capacityModel))
                .expectError(AlreadyHaveSameTechnologyAsociatedException.class)
                .verify();
    }
}

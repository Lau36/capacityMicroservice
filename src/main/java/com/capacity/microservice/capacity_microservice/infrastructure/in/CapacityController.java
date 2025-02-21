package com.capacity.microservice.capacity_microservice.infrastructure.in;

import com.capacity.microservice.capacity_microservice.application.dto.request.CapacityIdsRequest;
import com.capacity.microservice.capacity_microservice.application.dto.request.CapacityRequest;
import com.capacity.microservice.capacity_microservice.application.dto.response.CapacityResponse;
import com.capacity.microservice.capacity_microservice.application.handler.ICapacityRestHandler;
import com.capacity.microservice.capacity_microservice.domain.utils.CapacitiesAndTechnologiesPaginated;
import com.capacity.microservice.capacity_microservice.domain.utils.Pagination;
import com.capacity.microservice.capacity_microservice.domain.utils.SortDirection;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.capacity.microservice.capacity_microservice.infrastructure.utils.constants.InfraConstans.*;

@RestController()
@AllArgsConstructor
@RequestMapping(CAPACITY_PATH)
public class CapacityController {

    private final ICapacityRestHandler capacityRestHandler;

    @PostMapping
    public Mono<ResponseEntity<Void>> technology(@RequestBody CapacityRequest request) {
        return capacityRestHandler.createCapacity(request).then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @GetMapping
    public Mono<CapacitiesAndTechnologiesPaginated> getCapacitiesPaginated(@RequestParam int page,
                                                                             @RequestParam int size,
                                                                             @RequestParam String sort,
                                                                             @RequestParam String sortDirection) {
        Pagination pagination = new Pagination(page, size, sort, SortDirection.valueOf(sortDirection.toUpperCase()));
        return capacityRestHandler.getAllCapacities(pagination);
    }

    @PostMapping(LIST_CAPACITIES_PATH)
    public Flux<CapacityResponse> getCapacitiesByCapacitiesIds(@RequestBody CapacityIdsRequest request) {
        return capacityRestHandler.getCapacities(request.getCapacitiesIds());
    }

    @PostMapping(EXISTS_CAPACITIES_PATH)
    public Mono<Boolean> existsCapacities(@RequestBody CapacityIdsRequest request) {
        return capacityRestHandler.existCapacitiesById(request.getCapacitiesIds());
    }
}

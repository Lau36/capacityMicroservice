package com.capacity.microservice.capacity_microservice.infrastructure.out.adapter;

import com.capacity.microservice.capacity_microservice.application.dto.request.TechnologyCapacityRequest;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ITechnologyClientPort;
import com.capacity.microservice.capacity_microservice.domain.utils.constans.TechnologyIdsDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.capacity.microservice.capacity_microservice.infrastructure.utils.constants.InfraConstans.TECHNOLOGY_ASOCIATE_PATH;
import static com.capacity.microservice.capacity_microservice.infrastructure.utils.constants.InfraConstans.TECHNOLOGY_EXISTS_PATH;

@Component
@AllArgsConstructor
public class TechnologyClientAdapter implements ITechnologyClientPort {
    private final WebClient webClient;

    @Override
    public Mono<Boolean> existTechnologies(TechnologyIdsDTO technologyIds) {
        return webClient.post()
                .uri(TECHNOLOGY_EXISTS_PATH)
                .bodyValue(technologyIds)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    public Mono<Void> associateTechnologies(List<Long> technologyIds, Long capacityId) {
        return webClient.post()
                .uri(TECHNOLOGY_ASOCIATE_PATH)
                .bodyValue(new TechnologyCapacityRequest(capacityId, technologyIds))
                .retrieve()
                .bodyToMono(Void.class);
    }

}

package com.capacity.microservice.capacity_microservice.infrastructure.configuration;

import com.capacity.microservice.capacity_microservice.application.handler.ICapacityRestHandler;
import com.capacity.microservice.capacity_microservice.application.handler.impl.CapacityRestHandlerImpl;
import com.capacity.microservice.capacity_microservice.domain.ports.in.ICapacityServicePort;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ICapacityPersistencePort;
import com.capacity.microservice.capacity_microservice.domain.ports.out.ITechnologyClientPort;
import com.capacity.microservice.capacity_microservice.domain.useCase.CapacityUseCase;
import com.capacity.microservice.capacity_microservice.infrastructure.out.adapter.CapacityAdapter;
import com.capacity.microservice.capacity_microservice.infrastructure.out.repository.ICapacityRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfiguration {

    private final ICapacityRepository CapacityRepository;
    private final ITechnologyClientPort technologyClientPort;

    @Bean
    public ICapacityServicePort CapacityServicePort() {
        return new CapacityUseCase(CapacityPersistencePort(), technologyClientPort);
    }

    @Bean
    public ICapacityPersistencePort CapacityPersistencePort() {
        return new CapacityAdapter(CapacityRepository);
    }

    @Bean
    public ICapacityRestHandler CapacityRestHandler() {
        return new CapacityRestHandlerImpl(CapacityServicePort());
    }


}

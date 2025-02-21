package com.capacity.microservice.capacity_microservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CapacityIdsRequest {
    private List<Long> capacitiesIds;
}

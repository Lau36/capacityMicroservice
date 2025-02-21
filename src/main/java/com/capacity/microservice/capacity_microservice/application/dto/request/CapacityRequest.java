package com.capacity.microservice.capacity_microservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class CapacityRequest {
    private Long id;
    private String name;
    private String description;
    private List<String> technologiesIds;
}

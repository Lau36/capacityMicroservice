package com.capacity.microservice.capacity_microservice.application.dto.response;

import com.capacity.microservice.capacity_microservice.domain.utils.TechnologyIdName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CapacityResponse {
    private Long id;
    private String name;
    private List<TechnologyIdName> technologyIdNameList;
}

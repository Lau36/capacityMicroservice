package com.capacity.microservice.capacity_microservice.domain.utils;

import java.util.List;

public class TechnologyIdsDTO {
    public TechnologyIdsDTO(List<String> technologiesIds) {
        this.technologiesIds = technologiesIds;
    }

    public List<String> getTechnologiesIds() {
        return technologiesIds;
    }

    public void setTechnologiesIds(List<String> technologiesIds) {
        this.technologiesIds = technologiesIds;
    }

    private List<String> technologiesIds;
}

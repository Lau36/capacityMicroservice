package com.capacity.microservice.capacity_microservice.domain.utils.constans;

import java.util.List;

public class TechnologyCapacityDTO {
    public Long getCapacityId() {
        return capacityId;
    }

    public List<Long> getTechnologiesId() {
        return technologiesId;
    }

    public TechnologyCapacityDTO(Long capacityId, List<Long> technologiesId) {
        this.capacityId = capacityId;
        this.technologiesId = technologiesId;
    }

    public long setCapacityId(Long capacityId) {
        this.capacityId = capacityId;
        return capacityId;
    }

    public List<Long> setTechnologiesId(List<Long> technologiesId) {
        this.technologiesId = technologiesId;
        return technologiesId;
    }

    private Long capacityId;
    private List<Long> technologiesId;
}

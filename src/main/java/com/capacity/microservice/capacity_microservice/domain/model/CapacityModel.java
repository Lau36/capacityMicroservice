package com.capacity.microservice.capacity_microservice.domain.model;

import java.util.List;

public class CapacityModel {

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTechnologiesIds() {
        return technologiesIds;
    }

    public Integer getTechnologiesCount() {
        return technologiesCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTechnologiesIds(List<String> technologiesIds) {
        this.technologiesIds = technologiesIds;
    }

    public void setTechnologiesCount(Integer technologiesCount) {
        this.technologiesCount = technologiesCount;
    }

    public CapacityModel(Long id, String name, String description, List<String> technologiesIds, Integer technologiesCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technologiesIds = technologiesIds;
        this.technologiesCount = technologiesCount;
    }

    private Long id;
    private String name;
    private String description;
    private List<String> technologiesIds;
    private Integer technologiesCount;

}

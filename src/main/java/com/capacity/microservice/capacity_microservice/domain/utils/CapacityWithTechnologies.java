package com.capacity.microservice.capacity_microservice.domain.utils;

import java.util.List;

public class CapacityWithTechnologies {
    public CapacityWithTechnologies(Long id, String name, String description, List<TechnologyIdName> technologies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technologies = technologies;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<TechnologyIdName> getTechnologies() {
        return technologies;
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

    public void setTechnologies(List<TechnologyIdName> technologies) {
        this.technologies = technologies;
    }

    private Long id;
    private String name;
    private String description;
    private List<TechnologyIdName> technologies;
}

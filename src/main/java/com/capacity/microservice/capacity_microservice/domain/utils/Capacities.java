package com.capacity.microservice.capacity_microservice.domain.utils;

import java.util.List;

public class Capacities {
    public Capacities(Long id, String name, String description, List<Technologies> technologies) {
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

    public List<Technologies> getTechnologies() {
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

    public void setTechnologies(List<Technologies> technologies) {
        this.technologies = technologies;
    }

    private Long id;
    private String name;
    private String description;
    private List<Technologies> technologies;
}

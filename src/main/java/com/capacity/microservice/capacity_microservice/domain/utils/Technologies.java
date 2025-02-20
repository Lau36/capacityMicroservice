package com.capacity.microservice.capacity_microservice.domain.utils;

public class Technologies {
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Technologies(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;
}

package com.capacity.microservice.capacity_microservice.domain.utils;

import com.capacity.microservice.capacity_microservice.domain.model.CapacityModel;

import java.util.List;

public class CapacitiesPaginated {
    public CapacitiesPaginated(List<CapacityModel>  capacities, int currentPage, Long totalElements, int totalPages ) {
        this.capacities = capacities;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<CapacityModel>  getCapacities() {
        return capacities;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setCapacities(List<CapacityModel>  capacities) {
        this.capacities = capacities;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    private List<CapacityModel> capacities;
    private int currentPage;
    private Long totalElements;
    private int totalPages;
}

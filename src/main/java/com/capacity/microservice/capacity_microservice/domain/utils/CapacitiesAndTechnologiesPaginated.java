package com.capacity.microservice.capacity_microservice.domain.utils;

import java.util.List;

public class CapacitiesAndTechnologiesPaginated {
    public CapacitiesAndTechnologiesPaginated(List<Capacities> capacities, int currentPage, int totalPages, long totalElements) {
        this.capacities = capacities;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<Capacities> getCapacities() {
        return capacities;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setCapacities(List<Capacities> capacities) {
        this.capacities = capacities;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    private List<Capacities> capacities;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}

package com.capacity.microservice.capacity_microservice.domain.utils;

public class Pagination {
    private int page;
    private int size;
    private String sort;
    private SortDirection sortDirection;

    public Pagination(int page, int size, String sort, SortDirection sortDirection) {
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.sortDirection = sortDirection;
    }

    public String getSort() {
        return sort;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }
}

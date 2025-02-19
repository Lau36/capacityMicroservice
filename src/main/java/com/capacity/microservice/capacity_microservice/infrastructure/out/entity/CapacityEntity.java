package com.capacity.microservice.capacity_microservice.infrastructure.out.entity;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Setter
@Getter
@Table("capacity")
public class CapacityEntity {
    @Id
    private Long id;
    private String name;
    private String description;
}

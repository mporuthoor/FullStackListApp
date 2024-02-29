package com.project.listconstructorbackend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private String name;

    private String description;

    public BaseEntity(@NotNull String name) {
        this.name = name;
    }

    public BaseEntity(@NotNull String name, String description) {
        this.name = name;
        this.description = description;
    }

}

package com.project.listconstructorbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.NonNull;

import java.util.UUID;

@MappedSuperclass
public class ListItemEntity extends BaseEntity {

    @NonNull
    @Column(name = "list_id")
    private UUID listId;

    public ListItemEntity(
            @NonNull String name,
            @NonNull String description,
            @NonNull UUID listId) {

        super(name, description);
        this.listId = listId;
    }

}

package com.project.listconstructorbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
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

package com.project.listconstructorbackend.model;

import jakarta.persistence.Column;
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
public class ListItemEntity extends BaseEntity {

    // should not be changed once set
    @NotNull
    @Column(name = "list_id")
    private UUID listId;

    public ListItemEntity(@NotNull String name, @NotNull UUID listId) {
        super(name);
        this.listId = listId;
    }

    public ListItemEntity(@NotNull String name, String description, @NotNull UUID listId) {
        super(name, description);
        this.listId = listId;
    }

}

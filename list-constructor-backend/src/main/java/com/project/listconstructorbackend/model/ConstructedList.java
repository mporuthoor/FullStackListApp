package com.project.listconstructorbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "constructed_lists",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class ConstructedList extends BaseEntity {

    @NonNull
    private ConstructedListType type;

    @Column(name = "item_ids")
    private List<UUID> itemIds;

    public ConstructedList(
            @NonNull String name,
            @NonNull String description,
            @NonNull ConstructedListType type) {

        super(name, description);
        this.type = type;
    }

}

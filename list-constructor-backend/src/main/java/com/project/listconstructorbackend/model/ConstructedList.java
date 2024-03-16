package com.project.listconstructorbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "constructed_lists",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class ConstructedList extends BaseEntity {

    // should not be changed once set
    @NotNull
    private ConstructedListType type;

    @Column(name = "item_ids")
    private List<UUID> itemIds = new ArrayList<>();

    public ConstructedList(@NotNull String name, @NotNull ConstructedListType type) {
        super(name);
        this.type = type;
    }

    public ConstructedList(@NotNull String name, String description, @NotNull ConstructedListType type) {
        super(name, description);
        this.type = type;
    }

}

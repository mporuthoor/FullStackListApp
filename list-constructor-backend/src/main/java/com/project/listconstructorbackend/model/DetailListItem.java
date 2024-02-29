package com.project.listconstructorbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "detail_list_items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "listId"})})
public class DetailListItem extends ListItemEntity {

    @NotNull
    private List<String> details;

    public DetailListItem(
            @NotNull String name,
            @NotNull UUID listId,
            @NotNull List<String> details) {

        super(name, listId);
        this.details = details;
    }

    public DetailListItem(
            @NotNull String name,
            String description,
            @NotNull UUID listId,
            @NotNull List<String> details) {

        super(name, description, listId);
        this.details = details;
    }

}

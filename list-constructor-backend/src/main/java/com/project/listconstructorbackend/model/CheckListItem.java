package com.project.listconstructorbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "check_list_items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "listId"})})
public class CheckListItem extends ListItemEntity {

    private boolean checked;

    public CheckListItem(
            @NotNull String name,
            @NotNull UUID listId,
            boolean checked) {

        super(name, listId);
        this.checked = checked;
    }

    public CheckListItem(
            @NotNull String name,
            String description,
            @NotNull UUID listId,
            boolean checked) {

        super(name, description, listId);
        this.checked = checked;
    }

}

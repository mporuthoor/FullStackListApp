package com.project.listconstructorbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "check_list_items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "listId"})})
public class CheckListItem extends ListItemEntity {

    private boolean checked;

    public CheckListItem(
            @NonNull String name,
            @NonNull String description,
            @NonNull UUID listId,
            boolean checked) {

        super(name, description, listId);
        this.checked = checked;
    }

}

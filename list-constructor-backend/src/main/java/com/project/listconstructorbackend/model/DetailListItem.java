package com.project.listconstructorbackend.model;

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
@Table(name = "detail_list_items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "listId"})})
public class DetailListItem extends ListItemEntity {

    private List<String> titles;

    private List<String> values;

    public DetailListItem(
            @NonNull String name,
            @NonNull String description,
            @NonNull UUID listId,
            List<String> titles,
            List<String> values) {

        super(name, description, listId);
        this.titles = titles;
        this.values = values;
    }

}

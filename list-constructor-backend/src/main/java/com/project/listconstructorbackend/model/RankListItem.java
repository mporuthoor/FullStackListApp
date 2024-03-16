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
@Table(name = "rank_list_items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "listId"})})
public class RankListItem extends ListItemEntity {

    private int rank;

    public RankListItem(
            @NotNull String name,
            String description,
            @NotNull UUID listId,
            int rank) {

        super(name, description, listId);
        this.rank = rank;
    }

}

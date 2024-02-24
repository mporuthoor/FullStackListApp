package com.project.listconstructorbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "rank_list_items")
public class RankListItem extends ListItemEntity {

    private int rank;

    public RankListItem(
            @NonNull String name,
            @NonNull String description,
            @NonNull UUID listId,
            int rank) {

        super(name, description, listId);
        this.rank = rank;
    }

}
package com.project.listconstructorbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "constructed_lists")
public class ConstructedList {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Setter(AccessLevel.NONE)
    @Column(name = "type")
    private ConstructedListType type;

    @Column(name = "item_ids")
    private List<UUID> itemIds;

}

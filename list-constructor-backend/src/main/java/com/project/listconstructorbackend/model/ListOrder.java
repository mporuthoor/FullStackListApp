package com.project.listconstructorbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "List_order")
public class ListOrder {

    @Id
    @NotNull
    @Column(name = "list_id")
    private UUID listId;

    private short index;

}

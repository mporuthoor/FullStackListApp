package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.BaseEntity;
import com.project.listconstructorbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<T extends BaseEntity> {

    BaseRepository<T> getRepository();

    default Optional<T> getById(UUID id) {
        return getRepository().findById(id);
    }

    default List<T> getAll() {
        return getRepository().findAll();
    }

}

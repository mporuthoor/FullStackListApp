package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.BaseEntity;
import com.project.listconstructorbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<T extends BaseEntity> {

    BaseRepository<T> getRepository();

    default T create(T t) {
        return getRepository().save(t);
    }

    default Optional<T> get(UUID id) {
        return getRepository().findById(id);
    }

    default List<T> getAll() {
        return getRepository().findAll();
    }

    default Optional<T> update(T t) {
        if (getRepository().existsById(t.getId())) {
            return Optional.of(getRepository().save(t));
        }
        return Optional.empty();
    }

    default boolean delete(UUID id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
            return true;
        }
        return false;
    }

}

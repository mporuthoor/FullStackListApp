package com.project.listconstructorbackend.steps.common;

import com.project.listconstructorbackend.model.BaseEntity;
import com.project.listconstructorbackend.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

public interface BaseServiceHelper<T extends BaseEntity> {

    BaseService<T> getService();

    List<UUID> getTestIdsToPurge();

    void setTestIdsToPurge(List<UUID> ids);

    List<T> getActualList();

    void setActualList(List<T> list);

    default void noEntityExistsWithName(String name) {
        Optional<T> entity = CommonUtility.findByName(name, getService().getAll());

        entity.ifPresent(savedEntity -> getService().delete(savedEntity.getId()));
    }

    default void entitiesShouldExistWithDetails(List<T> expectedList, BiConsumer<T, T> function) {
        setActualList(getService().getAll());

        setTestIdsToPurge(EntityValidator.validateEntityLists(expectedList, getActualList(), function));
    }

    default void entityShouldNotExistWithName(String name) {
        Optional<T> savedEntity = CommonUtility.findByName(name, getActualList());

        assertTrue(savedEntity.isEmpty());
    }

    default void deleteEntitiesByIds() {
        getTestIdsToPurge().forEach(getService()::delete);
    }

}

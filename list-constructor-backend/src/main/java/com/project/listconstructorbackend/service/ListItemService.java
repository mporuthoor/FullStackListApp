package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.ListItemEntity;
import com.project.listconstructorbackend.repository.ListItemRepository;

import java.util.List;
import java.util.UUID;

public interface ListItemService<T extends ListItemEntity> extends BaseService<T> {

    default List<T> getByListId(UUID listId) {
        return ((ListItemRepository<T>) getRepository()).getByListId(listId);
    }

}

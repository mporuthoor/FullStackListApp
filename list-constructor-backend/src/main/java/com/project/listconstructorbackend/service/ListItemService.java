package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.exception.InvalidPayloadException;
import com.project.listconstructorbackend.exception.ResourceNotFoundException;
import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.model.ListItemEntity;
import com.project.listconstructorbackend.repository.ListItemRepository;
import com.project.listconstructorbackend.repository.ListRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ListItemService<T extends ListItemEntity> extends BaseService<T> {

    String CANNOT_CREATE_LIST_NOT_FOUND = "Item can't be created because list was not found with id: ";
    String CANNOT_GET_ITEMS_LIST_NOT_FOUND = "Items can't be retrieved because list was not found with id: ";
    String LIST_ID_NULL = "List id can't be null when creating list items";

    ListRepository getListRepository();

    default T create(T listItem) throws InvalidPayloadException, ResourceNotFoundException {
        UUID listId = listItem.getListId();
        if (listId == null) {
            throw new InvalidPayloadException(LIST_ID_NULL);
        }

        ConstructedList list = getListRepository().findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException(CANNOT_CREATE_LIST_NOT_FOUND + listId));

        T savedItem = getRepository().save(listItem);
        list.getItemIds().add(savedItem.getId());
        getListRepository().save(list);

        return savedItem;
    }

    default List<T> createMultiple(List<T> listItems) throws InvalidPayloadException, ResourceNotFoundException {
        HashMap<UUID, ConstructedList> idToListMap = new HashMap<>();
        for (T listItem: listItems) {
            UUID listId = listItem.getListId();
            if (listId == null) {
                throw new InvalidPayloadException(LIST_ID_NULL);
            } else if (idToListMap.containsKey(listId)) {
                continue;
            }

            ConstructedList list = getListRepository().findById(listId)
                    .orElseThrow(() -> new ResourceNotFoundException(CANNOT_CREATE_LIST_NOT_FOUND + listId));
            idToListMap.put(listId, list);
        }

        List<T> savedItems = getRepository().saveAll(listItems);
        for (T savedItem: savedItems) {
            UUID listId = savedItem.getListId();
            idToListMap.get(listId).getItemIds().add(savedItem.getId());
        }
        getListRepository().saveAll(idToListMap.values());

        return savedItems;
    }

    default List<T> getItemsByListId(UUID listId) throws ResourceNotFoundException {
        getListRepository().findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException(CANNOT_GET_ITEMS_LIST_NOT_FOUND + listId));

        return ((ListItemRepository<T>) getRepository()).findItemsByListId(listId);
    }

    default List<T> getAll() {
        return getRepository().findAll();
    }

    default Optional<T> update(T listItem) {
        Optional<T> saved = getRepository().findById(listItem.getId());

        // don't update saved ids
        return saved.map(savedItem -> {
            listItem.setListId(savedItem.getListId());

            return getRepository().save(listItem);
        });
    }

    default boolean delete(UUID id) {
        Optional<T> listItem = getRepository().findById(id);
        if (listItem.isEmpty()) {
            return false;
        }

        getRepository().deleteById(id);
        Optional<ConstructedList> list = getListRepository().findById(listItem.get().getListId());
        if (list.isPresent()) {
            list.get().getItemIds().remove(id);
            getListRepository().save(list.get());
        }

        return true;
    }

}

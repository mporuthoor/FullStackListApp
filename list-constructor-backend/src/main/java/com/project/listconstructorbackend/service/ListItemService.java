package com.project.listconstructorbackend.service;

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

    static final String CANNOT_CREATE_LIST_NOT_FOUND = "Item can't be created because list was not found for id: ";

    ListRepository getListRepository();

    default T create(T listItem) throws ResourceNotFoundException {
        UUID listId = listItem.getListId();
        ConstructedList list = getListRepository().findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException(CANNOT_CREATE_LIST_NOT_FOUND + listId));

        T savedItem = getRepository().save(listItem);
        list.getItemIds().add(savedItem.getId());
        getListRepository().save(list);

        return savedItem;
    }

    default List<T> createMultiple(List<T> listItems) throws ResourceNotFoundException {
        HashMap<UUID, ConstructedList> idToListMap = new HashMap<>();
        for (T listItem: listItems) {
            UUID listId = listItem.getListId();
            if (idToListMap.containsKey(listId)) {
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

    default List<T> getItemsByListId(UUID listId) {
        return ((ListItemRepository<T>) getRepository()).getItemsByListId(listId);
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

    default void deleteMultiple(List<UUID> itemIds) {
        getRepository().deleteAllById(itemIds);
    }

}

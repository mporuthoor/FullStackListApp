package com.project.listconstructorbackend.steps.common;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.ListItemEntity;
import com.project.listconstructorbackend.service.ListItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class ListItemServiceHelper<T extends ListItemEntity> implements BaseServiceHelper<T> {

    private final ListItemService<T> service;

    private final RestClient restClient;

    private List<UUID> testIdsToPurge = new ArrayList<>();

    private List<T> actualList;

    public void listItemsExistWithDetails(List<T> listItems) {
        List<T> savedItems = service.createMultiple(listItems);

        savedItems.stream().map(T::getId).forEachOrdered(testIdsToPurge::add);
    }

    public void noEntitiesExistsWithNames(String nameList) {
        HashSet<String> names = (HashSet<String>)
                Arrays.stream(nameList.split(", ")).collect(Collectors.toSet());
        Optional<List<T>> entities = CommonUtility.findAllByNames(names, service.getAll());

        entities.ifPresent(savedEntities -> savedEntities.forEach(
                savedEntity -> service.delete(savedEntity.getId())));
    }

}

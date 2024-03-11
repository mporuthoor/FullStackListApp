package com.project.listconstructorbackend.steps.common;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.BaseEntity;
import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.model.ListItemEntity;
import com.project.listconstructorbackend.repository.BaseRepository;
import com.project.listconstructorbackend.repository.ListRepository;
import com.project.listconstructorbackend.service.ListItemService;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class CommonRepositoryHelper<T extends BaseEntity> {

    private final BaseRepository<T> repository;

    private final RestClient restClient;

    private List<UUID> testIdsToPurge = new ArrayList<>();

    private List<T> actualList;

    public void createListWithDetailsAndGetListId(T list) {
        T savedList = repository.save(list);

        testIdsToPurge.add(savedList.getId());
        restClient.setListId(savedList.getId());
    }

    public void listIsOfSize(int size) {
        Optional<ConstructedList> savedList = ((ListRepository) repository).findById(restClient.getListId());

        assertTrue(savedList.isPresent());
        assertEquals(savedList.get().getItemIds().size(), size);
    }

    public void listsExistWithDetails(List<T> lists) {
        List<T> savedLists = repository.saveAll(lists);

        savedLists.stream().map(T::getId).forEachOrdered(testIdsToPurge::add);
    }

    public <U extends ListItemEntity> void listItemsExistWithDetails(List<U> entities, ListItemService<U> itemService) {
        List<U> savedItems = itemService.createMultiple(entities);

        savedItems.stream().map(U::getId).forEachOrdered(testIdsToPurge::add);
    }

    public void noEntityExistsWithName(String name) {
        Optional<T> entity = CommonUtility.findByName(name, repository.findAll());

        entity.ifPresent(savedEntity -> repository.deleteById(savedEntity.getId()));
    }

    public void noEntitiesExistsWithNames(String nameList) {
        HashSet<String> names = (HashSet<String>)
                Arrays.stream(nameList.split(", ")).collect(Collectors.toSet());
        Optional<List<T>> entities = CommonUtility.findAllByNames(names, repository.findAll());

        entities.ifPresent(savedEntities -> savedEntities.forEach(
                savedEntity -> repository.deleteById(savedEntity.getId())));
    }

    public void entitiesShouldExistWithDetails(List<T> expectedList, BiConsumer<T, T> function) {
        actualList = repository.findAll();

        testIdsToPurge = EntityValidator.validateEntityLists(expectedList, actualList, function);
    }

    public void entityShouldNotExistWithName(String name) {
        Optional<T> savedEntity = CommonUtility.findByName(name, actualList);

        assertTrue(savedEntity.isEmpty());
    }

    public void deleteEntitiesByIds() {
        repository.deleteAllById(testIdsToPurge);
    }

}

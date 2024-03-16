package com.project.listconstructorbackend.steps.common;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.service.ListService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Getter
@Setter
@RequiredArgsConstructor
public class ListServiceHelper implements BaseServiceHelper<ConstructedList>{

    private final ListService service;

    private final RestClient restClient;

    private List<UUID> testIdsToPurge = new ArrayList<>();

    private List<ConstructedList> actualList;

    public void createListWithDetailsAndGetListId(ConstructedList list) {
        ConstructedList savedList = service.create(list);

        testIdsToPurge.add(savedList.getId());
        restClient.setListId(savedList.getId());
    }

    public void listIsOfSize(int size) {
        Optional<ConstructedList> savedList = service.getById(restClient.getListId());

        assertTrue(savedList.isPresent());
        assertEquals(savedList.get().getItemIds().size(), size);
    }

    public void listsExistWithDetails(List<ConstructedList> lists) {
        List<ConstructedList> savedLists =
                lists.stream().map(service::create).collect(Collectors.toCollection(ArrayList::new));

        savedLists.stream().map(ConstructedList::getId).forEachOrdered(testIdsToPurge::add);
    }
}

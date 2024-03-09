package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.model.ConstructedListType;
import com.project.listconstructorbackend.repository.ListRepository;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class ListRepositorySteps {

    private final ListRepository listRepository;

    private List<UUID> testIdsToPurge;

    @DataTableType
    public ConstructedList listEntry(Map<String, String> entry) {
        String typeString = entry.get("type");
        ConstructedListType type = (typeString == null) ? null : ConstructedListType.valueOf(typeString);

        return new ConstructedList(entry.get("name"), entry.get("description"), type);
    }

    @Given("lists exist with the following details")
    public void listsExistWithDetails(List<ConstructedList> lists) {
        for (ConstructedList list: lists) {
            noListExistsWithName(list.getName());
        }

        List<ConstructedList> savedLists = listRepository.saveAll(lists);
        testIdsToPurge = savedLists.stream().map(ConstructedList::getId).toList();
    }

    @Given("no list exists with name {string}")
    public void noListExistsWithName(String name) {
        Optional<ConstructedList> list = listRepository.findListByName(name);

        list.ifPresent(constructedList -> listRepository.deleteById(constructedList.getId()));
    }

    @Then("lists should exist with the following details")
    public void listShouldExistWithName(List<ConstructedList> lists) {
        List<UUID> newIdsToPurge = new ArrayList<>();

        for (ConstructedList expected: lists) {
            Optional<ConstructedList> savedList = listRepository.findListByName(expected.getName());
            assertTrue(savedList.isPresent());

            ConstructedList actual = savedList.get();
            EntityValidator.validateList(expected, actual);
            newIdsToPurge.add(actual.getId());
        }

        testIdsToPurge = newIdsToPurge;
    }

    @Then("no list should exist with name {string}")
    public void listShouldNotExistWithName(String name) {
        Optional<ConstructedList> savedList = listRepository.findListByName(name);
        assertTrue(savedList.isEmpty());
    }

    @Then("I should purge the test lists from the database")
    public void deleteListsByIds() {
        listRepository.deleteAllById(testIdsToPurge);
    }

}

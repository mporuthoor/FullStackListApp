package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.repository.ListRepository;
import com.project.listconstructorbackend.steps.common.CommonRepositoryHelper;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

public class ListRepositorySteps {
    private final CommonRepositoryHelper<ConstructedList> helper;

    public ListRepositorySteps(ListRepository repository, RestClient client) {
        helper = new CommonRepositoryHelper<>(repository, client);
    }

    @Given("no list exists with name {string}")
    public void noListExistsWithName(String name) {
        helper.noEntityExistsWithName(name);
    }

    @Given("a list exists with the following details and I get its id")
    public void createListWithDetailsAndGetListId(ConstructedList list) {
        helper.createListWithDetailsAndGetListId(list);
    }

    @Given("the list is currently of size {int}")
    public void listIsOfSize(int size) {
        helper.listIsOfSize(size);
    }

    @Given("lists exist with the following details")
    public void listsExistWithDetails(List<ConstructedList> lists) {
        helper.listsExistWithDetails(lists);
    }

    @Then("lists should exist with the following details")
    public void listsShouldExistWithDetails(List<ConstructedList> expectedLists) {
        helper.entitiesShouldExistWithDetails(expectedLists, EntityValidator::validateList);
    }

    @Then("no list should exist with name {string}")
    public void listShouldNotExistWithName(String name) {
        helper.entityShouldNotExistWithName(name);
    }

    @Then("I should purge the test lists from the database")
    public void deleteListsByIds() {
        helper.deleteEntitiesByIds();
    }

}

package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.DetailListItem;
import com.project.listconstructorbackend.repository.DetailListItemRepository;
import com.project.listconstructorbackend.service.DetailListItemService;
import com.project.listconstructorbackend.steps.common.CommonRepositoryHelper;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

public class DetailListItemRepositorySteps {

    private final CommonRepositoryHelper<DetailListItem> helper;

    private final DetailListItemService detailListItemService;

    public DetailListItemRepositorySteps(
            DetailListItemRepository repository,
            RestClient client,
            DetailListItemService service) {

        detailListItemService = service;
        helper = new CommonRepositoryHelper<>(repository, client);
    }

    @Given("no detail list item exists with name {string}")
    public void noDetailListItemExistsWithName(String name) {
        helper.noEntityExistsWithName(name);
    }

    @Given("no detail list items exist with names {string}")
    public void noDetailListItemsExistWithNames(String nameList) {
        helper.noEntitiesExistsWithNames(nameList);
    }

    @Given("detail list items exist with that list id and the following details")
    public void detailListItemsExistWithDetails(List<DetailListItem> items) {
        helper.listItemsExistWithDetails(items, detailListItemService);
    }

    @Then("detail list items should exist with the following details")
    public void detailListItemsShouldExistWithDetails(List<DetailListItem> expectedList) {
        helper.entitiesShouldExistWithDetails(expectedList, EntityValidator::validateDetailListItem);
    }

    @Then("no detail list item should exist with name {string}")
    public void detailListItemShouldNotExistWithName(String name) {
        helper.entityShouldNotExistWithName(name);
    }

    @Then("I should purge the test detail list items from the database")
    public void deleteDetailListItemsByIds() {
        helper.deleteEntitiesByIds();
    }

}

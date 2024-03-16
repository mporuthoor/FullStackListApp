package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.CheckListItem;
import com.project.listconstructorbackend.service.CheckListItemService;
import com.project.listconstructorbackend.steps.common.ListItemServiceHelper;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

public class CheckListItemServiceSteps {

    private final ListItemServiceHelper<CheckListItem> helper;

    public CheckListItemServiceSteps(CheckListItemService service, RestClient client) {
        helper = new ListItemServiceHelper<>(service, client);
    }

    @Given("no check list item exists with name {string}")
    public void noCheckListItemExistsWithName(String name) {
        helper.noEntityExistsWithName(name);
    }

    @Given("no check list items exist with names {string}")
    public void noCheckListItemsExistWithNames(String nameList) {
        helper.noEntitiesExistsWithNames(nameList);
    }

    @Given("check list items exist with that list id and the following details")
    public void checkListItemsExistWithDetails(List<CheckListItem> items) {
        helper.listItemsExistWithDetails(items);
    }

    @Then("check list items should exist with the following details")
    public void checkListItemsShouldExistWithDetails(List<CheckListItem> expectedList) {
        helper.entitiesShouldExistWithDetails(expectedList, EntityValidator::validateCheckListItem);
    }

    @Then("no check list item should exist with name {string}")
    public void checkListItemShouldNotExistWithName(String name) {
        helper.entityShouldNotExistWithName(name);
    }

    @Then("I should purge the test check list items from the database")
    public void deleteCheckListItemsByIds() {
        helper.deleteEntitiesByIds();
    }

}

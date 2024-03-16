package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.RankListItem;
import com.project.listconstructorbackend.service.RankListItemService;
import com.project.listconstructorbackend.steps.common.ListItemServiceHelper;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

public class RankListItemServiceSteps {

    private final ListItemServiceHelper<RankListItem> helper;

    public RankListItemServiceSteps(RankListItemService service, RestClient client) {
        helper = new ListItemServiceHelper<>(service, client);
    }

    @Given("no rank list item exists with name {string}")
    public void noRankListItemExistsWithName(String name) {
        helper.noEntityExistsWithName(name);
    }

    @Given("no rank list items exist with names {string}")
    public void noRankListItemsExistWithNames(String nameList) {
        helper.noEntitiesExistsWithNames(nameList);
    }

    @Given("rank list items exist with that list id and the following details")
    public void rankListItemsExistWithDetails(List<RankListItem> items) {
        helper.listItemsExistWithDetails(items);
    }

    @Then("rank list items should exist with the following details")
    public void rankListItemsShouldExistWithDetails(List<RankListItem> expectedList) {
        helper.entitiesShouldExistWithDetails(expectedList, EntityValidator::validateRankListItem);
    }

    @Then("no rank list item should exist with name {string}")
    public void rankListItemShouldNotExistWithName(String name) {
        helper.entityShouldNotExistWithName(name);
    }

    @Then("I should purge the test rank list items from the database")
    public void deleteRankListItemsByIds() {
        helper.deleteEntitiesByIds();
    }

}

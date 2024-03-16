package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.RankListItem;
import com.project.listconstructorbackend.steps.common.CommonUtility;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class RankListItemSteps {

    private static final String RANK_LIST_ITEM_URL = "http://localhost:8080/api/v1/rankListItems";

    private final RestClient restClient;

    @DataTableType
    public RankListItem rankListItemEntry(Map<String, String> entry) {
        int rank = entry.get("rank") == null ? 0 : Integer.parseInt(entry.get("rank"));

        return new RankListItem(
                entry.get("name"),
                entry.get("description"),
                restClient.getListId(),
                rank);
    }

    @When("I create a rank list item with that list id and the following details")
    public void createRankListItemWithDetails(RankListItem rankListItem) {
        restClient.createRequest(RANK_LIST_ITEM_URL, rankListItem);
    }

    @When("I create multiple rank list items with that list id and the following details")
    public void createMultipleRankListItemsWithDetails(List<RankListItem> rankListItems) {
        restClient.createMultipleRequest(RANK_LIST_ITEM_URL, rankListItems);
    }

    @When("I get the id of rank list item {string}")
    public void getRankListItemIdByName(String name) {
        restClient.getEntityIdByName(RANK_LIST_ITEM_URL, name, RankListItem.class);
    }

    @When("I get the rank list item with that id")
    public void getRankListItemById() {
        restClient.getByIdRequest(RANK_LIST_ITEM_URL);
    }

    @When("I get all rank list items with that list id")
    public void getRankListItemsByListId() {
        restClient.getByListIdRequest(RANK_LIST_ITEM_URL);
    }

    @When("I get all rank list items")
    public void getAllRankListItems() {
        restClient.getAllRequest(RANK_LIST_ITEM_URL);
    }

    @When("I update the rank list item with that id and the following details")
    public void updateRankListItemById(RankListItem rankListItem) {
        restClient.updateByIdRequest(RANK_LIST_ITEM_URL, rankListItem);
    }

    @When("I delete the rank list item with that id")
    public void deleteRankListItemById() {
        restClient.deleteByIdRequest(RANK_LIST_ITEM_URL);
    }

    @Then("I should get a rank list item with the following details")
    public void shouldGetRankListItemWithDetails(RankListItem expected) {
        RankListItem actual =
                CommonUtility.getObjectFromResponseBody(restClient.getResponse(), RankListItem.class);
        EntityValidator.validateRankListItem(expected, actual);
    }

    @Then("I should only get rank list items with the following details")
    public void shouldOnlyGetRankListItemsWithDetails(List<RankListItem> expectedList) {
        List<RankListItem> actualList =
                CommonUtility.getListFromResponseBody(restClient.getResponse(), RankListItem.class);

        EntityValidator.validateSameSizeEntityLists(expectedList, actualList, EntityValidator::validateRankListItem);
    }

    @Then("I should get rank list items with the following details")
    public void shouldGetRankListItemsWithDetails(List<RankListItem> expectedList) {
        List<RankListItem> actualList =
                CommonUtility.getListFromResponseBody(restClient.getResponse(), RankListItem.class);

        EntityValidator.validateEntityLists(expectedList, actualList, EntityValidator::validateRankListItem);
    }

}

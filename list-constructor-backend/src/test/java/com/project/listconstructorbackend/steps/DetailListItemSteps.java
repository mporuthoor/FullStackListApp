package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.DetailListItem;
import com.project.listconstructorbackend.steps.common.CommonUtility;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DetailListItemSteps {

    private static final String DETAIL_LIST_ITEM_URL = "http://localhost:8080/api/v1/detailListItems";

    private final RestClient restClient;

    @DataTableType
    public DetailListItem detailListItemEntry(Map<String, String> entry) {
        List<String> details = entry.get("details") == null
                ? new ArrayList<>()
                : Arrays.asList(entry.get("details").split(", "));

        return new DetailListItem(
                entry.get("name"),
                entry.get("description"),
                restClient.getListId(),
                details);
    }

    @When("I create a detail list item with that list id and the following details")
    public void createDetailListItemWithDetails(DetailListItem detailListItem) {
        restClient.createRequest(DETAIL_LIST_ITEM_URL, detailListItem);
    }

    @When("I create multiple detail list items with that list id and the following details")
    public void createMultipleDetailListItemsWithDetails(List<DetailListItem> detailListItems) {
        restClient.createMultipleRequest(DETAIL_LIST_ITEM_URL, detailListItems);
    }

    @When("I get the id of detail list item {string}")
    public void getDetailListItemIdByName(String name) {
        restClient.getObjectIdByName(DETAIL_LIST_ITEM_URL, name, DetailListItem.class);
    }

    @When("I get the detail list item with that id")
    public void getDetailListItemById() {
        restClient.getByIdRequest(DETAIL_LIST_ITEM_URL);
    }

    @When("I get all detail list items with that list id")
    public void getDetailListItemsByListId() {
        restClient.getByListIdRequest(DETAIL_LIST_ITEM_URL);
    }

    @When("I get all detail list items")
    public void getAllDetailListItems() {
        restClient.getAllRequest(DETAIL_LIST_ITEM_URL);
    }

    @When("I update the detail list item with that id and the following details")
    public void updateDetailListItemById(DetailListItem detailListItem) {
        restClient.updateByIdRequest(DETAIL_LIST_ITEM_URL, detailListItem);
    }

    @When("I delete the detail list item with that id")
    public void deleteDetailListItemById() {
        restClient.deleteByIdRequest(DETAIL_LIST_ITEM_URL);
    }

    @Then("I should get a detail list item with the following details")
    public void shouldGetDetailListItemWithDetails(DetailListItem expected) {
        DetailListItem actual =
                CommonUtility.getObjectFromResponseBody(restClient.getResponse(), DetailListItem.class);
        EntityValidator.validateDetailListItem(expected, actual);
    }

    @Then("I should only get detail list items with the following details")
    public void shouldOnlyGetDetailListItemsWithDetails(List<DetailListItem> expectedList) {
        List<DetailListItem> actualList =
                CommonUtility.getListFromResponseBody(restClient.getResponse(), DetailListItem.class);

        EntityValidator.validateSameSizeEntityLists(expectedList, actualList, EntityValidator::validateDetailListItem);
    }

    @Then("I should get detail list items with the following details")
    public void shouldGetDetailListItemsWithDetails(List<DetailListItem> expectedList) {
        List<DetailListItem> actualList =
                CommonUtility.getListFromResponseBody(restClient.getResponse(), DetailListItem.class);

        EntityValidator.validateEntityLists(expectedList, actualList, EntityValidator::validateDetailListItem);
    }

}

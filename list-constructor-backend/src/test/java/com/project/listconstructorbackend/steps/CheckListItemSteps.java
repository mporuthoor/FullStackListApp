package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.CheckListItem;
import com.project.listconstructorbackend.steps.common.CommonUtility;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CheckListItemSteps {
    private static final String CHECK_LIST_ITEM_URL = "http://localhost:8080/api/v1/checkListItems";

    private final RestClient restClient;

    @DataTableType
    public CheckListItem checkListItemEntry(Map<String, String> entry) {

        return new CheckListItem(
                entry.get("name"),
                entry.get("description"),
                restClient.getListId(),
                Boolean.getBoolean(entry.get("checked")));
    }

    @When("I create a check list item with that list id and the following details")
    public void createCheckListItemWithDetails(CheckListItem checkListItem) {
        restClient.createRequest(CHECK_LIST_ITEM_URL, checkListItem);
    }

    @When("I create multiple check list items with that list id and the following details")
    public void createMultipleCheckListItemsWithDetails(List<CheckListItem> checkListItems) {
        restClient.createMultipleRequest(CHECK_LIST_ITEM_URL, checkListItems);
    }

    @When("I get the id of check list item {string}")
    public void getCheckListItemIdByName(String name) {
        restClient.getObjectIdByName(CHECK_LIST_ITEM_URL, name, CheckListItem.class);
    }

    @When("I get the check list item with that id")
    public void getCheckListItemById() {
        restClient.getByIdRequest(CHECK_LIST_ITEM_URL);
    }

    @When("I get all check list items with that list id")
    public void getCheckListItemsByListId() {
        restClient.getByListIdRequest(CHECK_LIST_ITEM_URL);
    }

    @When("I get all check list items")
    public void getAllCheckListItems() {
        restClient.getAllRequest(CHECK_LIST_ITEM_URL);
    }

    @When("I update the check list item with that id and the following details")
    public void updateCheckListItemById(CheckListItem checkListItem) {
        restClient.updateByIdRequest(CHECK_LIST_ITEM_URL, checkListItem);
    }

    @When("I delete the check list item with that id")
    public void deleteCheckListItemById() {
        restClient.deleteByIdRequest(CHECK_LIST_ITEM_URL);
    }

    @Then("I should get a check list item with the following details")
    public void shouldGetCheckListItemWithDetails(CheckListItem expected) {
        CheckListItem actual =
                CommonUtility.getObjectFromResponseBody(restClient.getResponse(), CheckListItem.class);
        EntityValidator.validateCheckListItem(expected, actual);
    }

    @Then("I should only get check list items with the following details")
    public void shouldOnlyGetCheckListItemsWithDetails(List<CheckListItem> expectedList) {
        List<CheckListItem> actualList =
                CommonUtility.getListFromResponseBody(restClient.getResponse(), CheckListItem.class);

        EntityValidator.validateSameSizeEntityLists(expectedList, actualList, EntityValidator::validateCheckListItem);
    }

    @Then("I should get check list items with the following details")
    public void shouldGetCheckListItemsWithDetails(List<CheckListItem> expectedList) {
        List<CheckListItem> actualList =
                CommonUtility.getListFromResponseBody(restClient.getResponse(), CheckListItem.class);

        EntityValidator.validateEntityLists(expectedList, actualList, EntityValidator::validateCheckListItem);
    }
}

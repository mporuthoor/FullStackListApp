package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.model.ConstructedListType;
import com.project.listconstructorbackend.steps.common.CommonUtility;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ListSteps {

    private static final String LIST_URL = "http://localhost:8080/api/v1/lists";

    private final RestClient restClient;

    @DataTableType
    public ConstructedList listEntry(Map<String, String> entry) {
        String typeString = entry.get("type");
        ConstructedListType type = (typeString == null) ? null : ConstructedListType.valueOf(typeString);

        return new ConstructedList(entry.get("name"), entry.get("description"), type);
    }

    @When("I create a list with the following details")
    public void createListWithDetails(ConstructedList list) {
        restClient.createRequest(LIST_URL, list);
    }

    @When("I get the id of list {string}")
    public void getListIdByName(String name) {
        restClient.getObjectIdByName(LIST_URL, name, ConstructedList.class);
    }

    @When("I get the list with that id")
    public void getListById() {
        restClient.getByIdRequest(LIST_URL);
    }

    @When("I get all lists")
    public void getAllLists() {
        restClient.getAllRequest(LIST_URL);
    }

    @When("I update the list with that id and the following details")
    public void updateListById(ConstructedList list) {
        restClient.updateByIdRequest(LIST_URL, list);
    }

    @When("I delete the list with that id")
    public void deleteListById() {
        restClient.deleteByIdRequest(LIST_URL);
    }

    @Then("I should get a list with the following details")
    public void shouldGetListWithDetails(ConstructedList expected) {
        ConstructedList actual =
                CommonUtility.getObjectFromResponseBody(restClient.getResponse(), ConstructedList.class);
        EntityValidator.validateList(expected, actual);
    }

    @Then("I should get lists with the following details")
    public void shouldGetListsWithDetails(List<ConstructedList> expectedLists) {
        List<ConstructedList> actualLists =
                CommonUtility.getListFromResponseBody(restClient.getResponse(), ConstructedList.class);

        EntityValidator.validateEntityLists(expectedLists, actualLists, EntityValidator::validateList);
    }

}

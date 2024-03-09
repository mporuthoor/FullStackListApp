package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.ConstructedList;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor
public class ListSteps {

    private static final String LIST_URL = "http://localhost:8080/api/v1/lists";

    private final RestClient restClient;

    @When("I create a list with the following details")
    public void createListWithDetails(ConstructedList list) {
        restClient.setResponse(restClient.getRequestSpecification().body(list).post(LIST_URL));
    }

    @When("I get a list by name {string}")
    public void getListByName(String name) {
        restClient.setName(name);
        restClient.setResponse(restClient.getRequestSpecification()
                .get(LIST_URL + "?name=" + name.replace(" ", "%20")));
    }

    @When("I get all lists")
    public void getAllLists() {
        restClient.setResponse(restClient.getRequestSpecification().get(LIST_URL + "/all"));
    }

    @When("I get the id of list {string}")
    public void getIdByName(String name) {
        restClient.setResponse(restClient.getRequestSpecification()
                .get(LIST_URL + "?name=" + name.replace(" ", "%20")));
        assertEquals(200, restClient.getResponse().getStatusCode());

        restClient.setId(restClient.getResponse().body().as(ConstructedList.class).getId());
        assertNotNull(restClient.getId());
    }

    @When("I get the list with that id")
    public void getListById() {
        restClient.setResponse(restClient.getRequestSpecification().get(LIST_URL + "/" + restClient.getId()));
    }

    @When("I update the list with that id and the following details")
    public void updateListById(ConstructedList list) {
        restClient.setResponse(restClient.getRequestSpecification().body(list)
                .put(LIST_URL + "/" + restClient.getId()));
    }

    @When("I delete the list with that id")
    public void deleteListById() {
        restClient.setResponse(restClient.getRequestSpecification().delete(LIST_URL + "/" + restClient.getId()));
    }

    @Then("I should get a list with the following details")
    public void shouldGetListWithDetails(ConstructedList expected) {
        ConstructedList actual = restClient.getResponse().body().as(ConstructedList.class);
        EntityValidator.validateList(expected, actual);
    }

    @Then("I should get lists with the following details")
    public void shouldGetListsWithDetails(List<ConstructedList> expectedLists) {
        List<ConstructedList> actualLists =
                restClient.getResponse().body().jsonPath().getList("", ConstructedList.class);
        Map<String, ConstructedList> actualMap =
                actualLists.stream().collect(Collectors.toMap(ConstructedList::getName, Function.identity()));

        for (ConstructedList expected: expectedLists) {
            ConstructedList actual = actualMap.get(expected.getName());
            EntityValidator.validateList(expected, actual);
        }
    }

}

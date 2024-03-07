package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.model.ConstructedList;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor
public class ListSteps {

    private static final String LIST_URL = "http://localhost:8080/api/v1/lists";

    private final RestClient restClient;
    private Response response;
    private UUID id;

    @When("I create a list with the following details")
    public void createListWithDetails(ConstructedList list) {
        response = restClient.getRequestSpecification().body(list).post(LIST_URL);
        assertEquals(200, response.getStatusCode());
    }

    @When("I get a list by name {string}")
    public void getListByName(String name) {
        response = restClient.getRequestSpecification()
                .get(LIST_URL + "?name=" + name.replace(" ", "%20"));
        assertEquals(200, response.getStatusCode());
    }

    @When("I get all lists")
    public void getAllLists() {
        response = restClient.getRequestSpecification().get(LIST_URL + "/all");
        assertEquals(200, response.getStatusCode());
    }

    @And("I get the id of list {string}")
    public void getIdByName(String name) {
        response = restClient.getRequestSpecification()
                .get(LIST_URL + "?name=" + name.replace(" ", "%20"));
        assertEquals(200, response.getStatusCode());

        id = response.body().as(ConstructedList.class).getId();
        assertNotNull(id);
    }

    @When("I get the list with that id")
    public void getListById() {
        response = restClient.getRequestSpecification().get(LIST_URL + "/" + id);
        assertEquals(200, response.getStatusCode());
    }

    @When("I update the list with that id and the following details")
    public void updateListById(ConstructedList list) {
        response = restClient.getRequestSpecification().body(list).put(LIST_URL + "/" + id);
        assertEquals(200, response.getStatusCode());
    }

    @When("I delete the list with that id")
    public void deleteListById() {
        response = restClient.getRequestSpecification().delete(LIST_URL + "/" + id);
        assertEquals(200, response.getStatusCode());
    }

    @Then("I should get a list with the following details")
    public void shouldGetListWithDetails(ConstructedList expected) {
        ConstructedList actual = response.body().as(ConstructedList.class);
        EntityValidator.validateList(expected, actual);
    }

    @Then("I should get lists with the following details")
    public void shouldGetListsWithDetails(List<ConstructedList> expectedLists) {
        List<ConstructedList> actualLists = response.body().jsonPath().getList("", ConstructedList.class);
        Map<String, ConstructedList> actualMap =
                actualLists.stream().collect(Collectors.toMap(ConstructedList::getName, Function.identity()));

        for (ConstructedList expected: expectedLists) {
            ConstructedList actual = actualMap.get(expected.getName());
            EntityValidator.validateList(expected, actual);
        }
    }

}






















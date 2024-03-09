package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.exception.DetailedErrorInfo;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class ErrorSteps {

    private final RestClient restClient;

    @DataTableType
    public DetailedErrorInfo detailedErrorInfoEntry(Map<String, String> entry) {
        return new DetailedErrorInfo(
                entry.get("message"),
                entry.get("messageDetails"),
                Arrays.asList(entry.get("messageDetails").split(", ")));
    }

    @When("I get a randomly generated id")
    public void getRandomId() {
        restClient.setId(UUID.randomUUID());
    }

    @Then("I should get no error")
    public void shouldGetNoError(){
        assertEquals(200, restClient.getResponse().getStatusCode());
    }

    @Then("I should get a bad request error with the following message and message details")
    public void shouldGetBadRequestWithMessageAndDetails(DetailedErrorInfo errorInfo) {
        assertEquals(400, restClient.getResponse().getStatusCode());
        EntityValidator.validateDetailedErrorData(errorInfo, restClient.getResponse());
    }

    @Then("I should get a resource not found by name error")
    public void shouldGetResourceNotFoundByName() {
        String message = restClient.getResponse().body().jsonPath().get("message");

        assertEquals(404, restClient.getResponse().getStatusCode());
        assertTrue(message.contains("not found with name: " + restClient.getName()));
    }

    @Then("I should get a resource not found by id error")
    public void shouldGetResourceNotFoundById() {
        String message = restClient.getResponse().body().jsonPath().get("message");

        assertEquals(404, restClient.getResponse().getStatusCode());
        assertTrue(message.contains("not found with id: " + restClient.getId()));
    }

}

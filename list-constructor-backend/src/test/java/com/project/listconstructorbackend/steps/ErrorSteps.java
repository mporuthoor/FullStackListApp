package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.client.RestClient;
import com.project.listconstructorbackend.exception.DetailedErrorInfo;
import com.project.listconstructorbackend.steps.common.CommonUtility;
import com.project.listconstructorbackend.steps.common.EntityValidator;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class ErrorSteps {

    private final RestClient restClient;

    @DataTableType
    public DetailedErrorInfo detailedErrorInfoEntry(Map<String, String> entry) {
        List<String> details = entry.get("messageDetails") == null
                ? null
                : Arrays.asList(entry.get("messageDetails").split(", "));

        return new DetailedErrorInfo(
                entry.get("message"),
                entry.get("requestDetails"),
                details);
    }

    @Given("I get a randomly generated id")
    public void getRandomId() {
        restClient.setId(UUID.randomUUID());
    }

    @Given("I have no list id")
    public void clearListId() {
        restClient.setListId(null);
    }

    @Given("I get a randomly generated list id")
    public void getRandomListId() {
        restClient.setListId(UUID.randomUUID());
    }

    @Then("I should get no error")
    public void shouldGetNoError() {
        assertEquals(200, restClient.getResponse().getStatusCode());
    }

    @Then("I should get a bad request error with the following message and message details")
    public void shouldGetBadRequestWithMessageAndDetails(DetailedErrorInfo errorInfo) {
        EntityValidator.validateDetailedErrorData(errorInfo, restClient.getResponse());
    }

    @Then("I should get a resource not found by id error")
    public void shouldGetResourceNotFoundById() {
        String message = CommonUtility.getErrorMessage(restClient.getResponse());

        assertEquals(404, restClient.getResponse().getStatusCode());
        assertTrue(message.contains("not found with id: " + restClient.getId()));
    }

    @Then("I should get a resource not found by list id error")
    public void shouldGetResourceNotFoundByListId() {
        String message = CommonUtility.getErrorMessage(restClient.getResponse());

        assertEquals(404, restClient.getResponse().getStatusCode());
        assertTrue(message.contains("not found with id: " + restClient.getListId()));
    }

}

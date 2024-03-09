package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.exception.DetailedErrorInfo;
import com.project.listconstructorbackend.model.ConstructedList;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityValidator {

    public static void validateList(ConstructedList expected, ConstructedList actual) {
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertIterableEquals(expected.getItemIds(), actual.getItemIds());
    }

    public static void validateDetailedErrorData(DetailedErrorInfo errorInfo, Response response) {
        String message = response.body().jsonPath().get("message");
        List<String> details = response.body().jsonPath().getList("messageDetails");

        assertEquals(message, errorInfo.getMessage());
        assertTrue(errorInfo.getMessageDetails().containsAll(details));
    }
}

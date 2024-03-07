package com.project.listconstructorbackend.steps;

import com.project.listconstructorbackend.model.ConstructedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class EntityValidator {

    public static void validateList(ConstructedList expected, ConstructedList actual) {
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertIterableEquals(expected.getItemIds(), actual.getItemIds());
    }
}

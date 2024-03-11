package com.project.listconstructorbackend.steps.common;

import com.project.listconstructorbackend.exception.DetailedErrorInfo;
import com.project.listconstructorbackend.model.*;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class EntityValidator {

    public static void validateList(ConstructedList expected, ConstructedList actual) {
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertIterableEquals(expected.getItemIds(), actual.getItemIds());
    }

    private static <T extends ListItemEntity> void validateListItem(T expected, T actual) {
        assertEquals(expected.getListId(), actual.getListId());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    public static void validateCheckListItem(CheckListItem expected, CheckListItem actual) {
        validateListItem(expected, actual);
        assertEquals(expected.isChecked(), actual.isChecked());
    }

    public static void validateDetailListItem(DetailListItem expected, DetailListItem actual) {
        validateListItem(expected, actual);
        assertIterableEquals(expected.getDetails(), actual.getDetails());
    }

    public static void validateRankListItem(RankListItem expected, RankListItem actual) {
        validateListItem(expected, actual);
        assertEquals(expected.getRank(), actual.getRank());
    }

    public static <T extends BaseEntity> List<UUID> validateEntityLists(
            List<T> expectedList,
            List<T> actualList,
            BiConsumer<T, T> function) {
        List<UUID> newIdsToPurge = new ArrayList<>();
        HashMap<String, T> actualMap = (HashMap<String, T>)
                actualList.stream().collect(Collectors.toMap(T::getName, Function.identity()));

        for (T expected : expectedList) {
            T actual = actualMap.get(expected.getName());
            function.accept(expected, actual);
            newIdsToPurge.add(actual.getId());
        }

        return newIdsToPurge;
    }

    public static <T extends BaseEntity> void validateSameSizeEntityLists(
            List<T> expectedList,
            List<T> actualList,
            BiConsumer<T, T> function) {

        assertEquals(expectedList.size(), actualList.size());
        validateEntityLists(expectedList, actualList, function);
    }

    public static void validateDetailedErrorData(DetailedErrorInfo errorInfo, Response response) {
        String message = CommonUtility.getErrorMessage(response);
        List<String> details = response.body().jsonPath().getList("messageDetails");

        assertEquals(400, response.getStatusCode());
        assertEquals(errorInfo.getMessage(), message);

        if (errorInfo.getMessageDetails() != null) {
            assertTrue(errorInfo.getMessageDetails().containsAll(details));
        }
    }

}

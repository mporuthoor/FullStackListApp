package com.project.listconstructorbackend.steps.common;

import com.project.listconstructorbackend.model.BaseEntity;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CommonUtility {

    public static <T extends BaseEntity> Optional<T> findByName(String name, List<T> entityObjects) {
        for (T entityObject : entityObjects) {
            if (entityObject.getName().equals(name)) {
                return Optional.of(entityObject);
            }
        }

        return Optional.empty();
    }

    public static <T extends BaseEntity> Optional<List<T>> findAllByNames(Set<String> names, List<T> entityObjects) {
        ArrayList<T> foundObjects = new ArrayList<>();

        for (T entityObject : entityObjects) {
            if (names.contains(entityObject.getName())) {
                foundObjects.add(entityObject);
            }
        }

        return foundObjects.isEmpty() ? Optional.empty() : Optional.of(foundObjects);
    }

    public static <T extends BaseEntity> T getObjectFromResponseBody(Response response, Class<T> entityClass) {
        return response.body().as(entityClass);
    }

    public static <T extends BaseEntity> List<T> getListFromResponseBody(Response response, Class<T> entityClass) {
        return response.body().jsonPath().getList("", entityClass);
    }

    public static String getErrorMessage(Response response) {
        return response.body().jsonPath().get("message");
    }

}

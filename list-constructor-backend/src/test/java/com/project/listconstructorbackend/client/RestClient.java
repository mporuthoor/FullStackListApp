package com.project.listconstructorbackend.client;

import com.project.listconstructorbackend.model.BaseEntity;
import com.project.listconstructorbackend.steps.common.CommonUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Component
public class RestClient {

    private Response response;

    private UUID id;

    private UUID listId;

    private RequestSpecification getRequestSpecification() {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .urlEncodingEnabled(false)
                .when()
                .log()
                .everything();
    }

    public <T extends BaseEntity> void createRequest(String url, T body) {
        response = getRequestSpecification().body(body).post(url);
    }

    public <T extends BaseEntity> void createMultipleRequest(String url, List<T> body) {
        response = getRequestSpecification().body(body).post(url + "/batch");
    }

    public void getAllRequest(String url) {
        response = getRequestSpecification().get(url + "/all");
    }

    public void getByIdRequest(String url) {
        response = getRequestSpecification().get(url + "/" + id);
    }

    public void getByListIdRequest(String url) {
        response = getRequestSpecification().get(url + "/list/" + listId);
    }

    public <T extends BaseEntity> void updateByIdRequest(String url, T body) {
        response = getRequestSpecification().body(body).put(url + "/" + id);
    }

    public void deleteByIdRequest(String url) {
        response = getRequestSpecification().delete(url + "/" + id);
    }

    public <T extends BaseEntity> void getObjectIdByName(String url, String name, Class<T> entityClass) {
        getAllRequest(url);
        Optional<T> savedList = CommonUtility.findByName(
                name,
                CommonUtility.getListFromResponseBody(response, entityClass));

        assertTrue(savedList.isPresent());
        id = savedList.get().getId();
    }

}

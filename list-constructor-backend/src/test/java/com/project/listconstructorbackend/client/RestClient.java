package com.project.listconstructorbackend.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Component
public class RestClient {

    private Response response;

    private UUID id;

    private String name;

    public RequestSpecification getRequestSpecification() {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .urlEncodingEnabled(false)
                .when()
                .log()
                .everything();
    }
}

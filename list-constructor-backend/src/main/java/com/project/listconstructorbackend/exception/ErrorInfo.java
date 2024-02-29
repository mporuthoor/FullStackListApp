package com.project.listconstructorbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorInfo {

    private String message;
    private String requestDetails;

}

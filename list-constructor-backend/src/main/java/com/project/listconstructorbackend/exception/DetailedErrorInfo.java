package com.project.listconstructorbackend.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class DetailedErrorInfo extends ErrorInfo{

    private List<String> messageDetails;

    public DetailedErrorInfo(String message, String requestDetails, List<String> messageDetails) {
        super(message, requestDetails);
        this.messageDetails = messageDetails;
    }
}
